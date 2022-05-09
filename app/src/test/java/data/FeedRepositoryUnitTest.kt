package data

import org.junit.Test

import com.lightricks.feedexercise.network.FeedApiService
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest

class FeedRepositoryUnitTest(private val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) {

    @Test
    fun FeedRepository_getTemplateMetadata_NotNull() = testCoroutineDispatcher.runBlockingTest{
        val feedApiService = FeedApiService.create()
        val response = feedApiService.getTemplatesMetadata()
        println(response)
        assertNotNull(response)
    }
}
