package com.lightricks.feedexercise.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface FeedApiService {
    companion object {
        val BASE_URL: String = "https://assets.swishvideoapp.com/Android/demo/"

        fun create() : FeedApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(FeedApiService::class.java)
        }
    }

    @GET("feed.json")
    fun getTemplatesMetadata(): Call<TemplatesMetadata>
}