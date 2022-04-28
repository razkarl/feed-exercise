package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.network.FeedApiService
import com.lightricks.feedexercise.network.TemplatesMetadata

/**
 * This is our data layer abstraction. Users of this class don't need to know
 * where the data actually comes from (network, database or somewhere else).
 */
class FeedRepository {
    private val feedApiService = FeedApiService.create()

    fun getTemplatesMetadata(): TemplatesMetadata? {
        val templatesMetadata = feedApiService.getTemplatesMetadata()
        return templatesMetadata.execute().body()
    }
}