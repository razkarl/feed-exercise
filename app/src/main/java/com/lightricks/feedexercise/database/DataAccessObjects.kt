package com.lightricks.feedexercise.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FeedItemsDao {

    // Method for inserting a list of entities.
    @Insert
    fun insertAll(vararg feedItemEntities: List<FeedItemEntity>): Completable

    // Method for deleting all entities.
    @Query("DELETE FROM feedItems")
    fun deleteAll(): Completable

    // Method for querying all entities.
    @Query("SELECT * FROM feedItems")
    fun getAll(): Observable<List<FeedItemEntity>>

    // Method for querying the count of entities.
    @Query("SELECT COUNT(*) FROM feedItems")
    fun count(): Single<Int>

}