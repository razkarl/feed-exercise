package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.network.FeedApiService
import com.lightricks.feedexercise.network.FeedApiService.Companion.THUMBNAILS_URL
import com.lightricks.feedexercise.network.TemplateMetadata
import com.lightricks.feedexercise.network.TemplatesMetadata
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This is our data layer abstraction. Users of this class don't need to know
 * where the data actually comes from (network, database or somewhere else).
 */
class FeedRepository(
    private val feedApiService: FeedApiService = FeedApiService.create(),
    private val cachedFeedItems: MutableStateFlow<List<FeedItem>> = MutableStateFlow(emptyList())
) {

    fun feedItemsFlow(): Flow<List<FeedItem>> = cachedFeedItems

    suspend fun refreshFeedItems() {
        val templatesMetadata: TemplatesMetadata = feedApiService.getTemplatesMetadata()
        val refreshedFeedItems = templatesMetadata.templates.map { templateMetadata ->
            feedItemFromTemplateMetadata(templateMetadata)
        }
        cachedFeedItems.value = refreshedFeedItems
    }

    private fun feedItemFromTemplateMetadata(templateMetadata: TemplateMetadata): FeedItem =
        FeedItem(
            id = templateMetadata.id,
            thumbnailUrl = THUMBNAILS_URL + templateMetadata.templateThumbnailURI,
            isPremium = templateMetadata.isPremium
        )
}