package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.database.FeedItemEntity
import com.lightricks.feedexercise.database.FeedItemsDao
import com.lightricks.feedexercise.network.FeedApiService
import com.lightricks.feedexercise.network.FeedApiService.Companion.THUMBNAILS_URL
import com.lightricks.feedexercise.network.TemplateMetadata
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * This is our data layer abstraction. Users of this class don't need to know
 * where the data actually comes from (network, database or somewhere else).
 */
class FeedRepository(
    private val feedItemsDao: FeedItemsDao,
    private val feedApiService: FeedApiService = FeedApiService.create()) {
    private val cachedFeedItems: Flow<List<FeedItemEntity>> = feedItemsDao.getAll()

    fun feedItemsFlow(): Flow<List<FeedItem>> = cachedFeedItems.map { entities ->
        entities.map { entity ->
            FeedItem(
                entity.id,
                entity.thumbnailUrl,
                entity.isPremium
            )
        }
    }

    suspend fun refreshFeedItems() {
        feedApiService.getTemplatesMetadata().templates.forEach { templateMetadata ->
            val feedItemEntity = feedItemEntityFromTemplateMetadata(templateMetadata)
            // Insert items to DB
            feedItemsDao.insertFeedItemEntity(feedItemEntity)
        }
    }

    private fun feedItemEntityFromTemplateMetadata(templateMetadata: TemplateMetadata): FeedItemEntity =
        FeedItemEntity(
            id = templateMetadata.id,
            thumbnailUrl = THUMBNAILS_URL + templateMetadata.templateThumbnailURI,
            isPremium = templateMetadata.isPremium
        )
}
