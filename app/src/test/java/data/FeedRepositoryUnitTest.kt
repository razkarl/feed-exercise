package data

import org.junit.Test

import com.lightricks.feedexercise.data.FeedRepository
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking

class FeedRepositoryUnitTest {
    @Test
    fun FeedRepository_getTemplateMetadata_NotNull(){
        val feedApiService = FeedRepository()
        runBlocking {
            val response = feedApiService.getTemplatesMetadata()
            println(response)
            assertNotNull(response)
        }
    }
}
