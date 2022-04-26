package com.lightricks.feedexercise.data

import org.junit.Test

// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth.assertThat

class FeedRepositoryUnitTest {
   //todo: add the tests here

    @Test
    fun FeedRepository_ValidGetRequest_ReturnsValidResponse(){
        val feedApiService = FeedRepository()
        val response = feedApiService.getRequest("BikeThumbnail.jpg")
        assertThat(response.toString().isNotEmpty())
    }
}
