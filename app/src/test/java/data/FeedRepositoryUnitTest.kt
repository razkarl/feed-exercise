package data

import org.junit.Test

import com.lightricks.feedexercise.data.FeedRepository
import junit.framework.Assert.assertNotNull

class FeedRepositoryUnitTest {
    @Test
    fun FeedRepository_getTemplateMetadata_NotNull(){
        val feedApiService = FeedRepository()
        val response = feedApiService.getTemplatesMetadata()
        println(response)
        assertNotNull(response)
    }
}
