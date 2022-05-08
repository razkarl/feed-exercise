package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.network.FeedApiService
import com.lightricks.feedexercise.network.FeedApiService.Companion.THUMBNAILS_URL
import com.lightricks.feedexercise.network.TemplateMetadata
import com.lightricks.feedexercise.network.TemplatesMetadata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

/**
 * This is our data layer abstraction. Users of this class don't need to know
 * where the data actually comes from (network, database or somewhere else).
 */
class FeedRepository(
    private val feedApiService: FeedApiService = FeedApiService.create()
) {
    private val cachedFeedItems = MutableStateFlow<List<FeedItem>>(emptyList())

    fun feedItemsFlow(): Flow<List<FeedItem>> = cachedFeedItems

    suspend fun refreshFeedItems() {
        withContext(Dispatchers.IO){
            val templatesMetadata: TemplatesMetadata = feedApiService.getTemplatesMetadata()
            val refreshedFeedItemEntities = templatesMetadata.templates.map { templateMetadata ->
                feedItemEntityFromTemplateMetadata(templateMetadata)
            }
            cachedFeedItems.value = refreshedFeedItemEntities
        }
    }

    private fun feedItemEntityFromTemplateMetadata(templateMetadata: TemplateMetadata): FeedItem =
        FeedItem(
            id = templateMetadata.id,
            thumbnailUrl = THUMBNAILS_URL + templateMetadata.templateThumbnailURI,
            isPremium = templateMetadata.isPremium
        )

    companion object {
        val instance by lazy { FeedRepository() }
    }
}
