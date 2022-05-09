package com.lightricks.feedexercise.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedItemsDao {

    // Method for inserting a list of entities.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFeedItemEntity(feedItemEntity: FeedItemEntity)

    // Method for deleting all entities.
    @Query("DELETE FROM feedItems")
//    @Delete
    suspend fun deleteAll()

    // Method for querying all entities.
    @Query("SELECT * FROM feedItems")
    fun getAll(): Flow<List<FeedItemEntity>>

    // Method for querying the count of entities.
    @Query("SELECT COUNT(*) FROM feedItems")
    suspend fun count(): Int

}