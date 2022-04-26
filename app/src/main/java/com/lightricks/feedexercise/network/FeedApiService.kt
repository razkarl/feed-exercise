package com.lightricks.feedexercise.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * todo: add the FeedApiService interface and the Retrofit and Moshi code here
 */
interface FeedApiService {
    companion object {
        val baseURL: String = "http://assets.swishvideoapp.com/Android/demo/catalog/thumbnails/"
    }

    // Execute a GET request and return an RxJava’s Single that contains the response.
    @GET("{uri}")
    fun getRequest(@Path("uri") uri: String): Call<Single<GetFeedResponse>>
}

class GetFeedResponse {
    // RKARL: TODO - Implement with Moshi
    // Use Moshi to de-JSONify things
}
