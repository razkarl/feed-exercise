package com.lightricks.feedexercise.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lightricks.feedexercise.data.FeedItem

@Database(entities = [FeedItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedItemsDao(): FeedItemsDao
}