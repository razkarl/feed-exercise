package com.lightricks.feedexercise.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataAccessObjectsTest {
    @Test
    fun EmptyTable_InsertSomething_CountIsOne() {
        val db = Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java, "database-name"
        ).build()

        // Empty DB has 0 items
        assertThat(db.feedItemsDao().count()).isEqualTo(0)

        // Insert 1 item to DB, now has 1 items
        val demoFeedItemEntity =
            FeedItemEntity(id = "id", thumbnailUrl = "thumbnailUrl", isPremium = false)
        db.feedItemsDao().insertAll(listOf(demoFeedItemEntity))
        assertThat(db.feedItemsDao().count()).isEqualTo(1)
    }
}