package com.lightricks.feedexercise.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [FeedItemEntity::class], version = 1)
abstract class FeedItemsDatabase : RoomDatabase() {
    abstract fun feedItemsDao(): FeedItemsDao


    private class FeedItemsDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.feedItemsDao())
                }
            }
        }

        suspend fun populateDatabase(deefItemsDao: FeedItemsDao) {
            deefItemsDao.deleteAll()
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FeedItemsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): FeedItemsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeedItemsDatabase::class.java,
                    "feedItemsDatabase"
                )
                    .addCallback(FeedItemsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}