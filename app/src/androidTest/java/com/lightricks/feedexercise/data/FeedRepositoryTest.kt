package com.lightricks.feedexercise.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

// for assertions on Java 8 types (Streams and java.util.Optional)
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage

@RunWith(AndroidJUnit4::class)
class FeedRepositoryTest {
   //todo: add the tests here

    @Test
    fun FeedRepository_ValidGetRequest_ReturnsValidResponse(){
        val feedApiService = FeedRepository()
        val response = feedApiService.getRequest("BikeThumbnail.jpg")
        assertThat(response)
    }
}
