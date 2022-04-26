package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.network.FeedApiService
import com.lightricks.feedexercise.network.GetFeedResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit

/**
 * This is our data layer abstraction. Users of this class don't need to know
 * where the data actually comes from (network, database or somewhere else).
 */
class FeedRepository {
    private lateinit var retrofit: Retrofit
    private lateinit var service: FeedApiService

    constructor() {
        retrofit = Retrofit.Builder().baseUrl(FeedApiService.baseURL).build()
        service = retrofit.create(FeedApiService::class.java)
    }

    fun getRequest(request: String): Call<Single<GetFeedResponse>> {
        return service.getRequest(request)
    }
}