package network

import org.junit.Test

import com.lightricks.feedexercise.network.Nissim
import com.squareup.moshi.Moshi
import junit.framework.Assert.assertEquals

class FeedRepositoryUnitTest {
    @Test
    fun NissimClass_ConvertToJsonWithMoshi_ConversionCorrect(){
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Nissim::class.java)
        val nissinInstance = Nissim(a = "arfi")
        assertEquals("{\"a\":\"arfi\"}", adapter.toJson(nissinInstance))
    }
}