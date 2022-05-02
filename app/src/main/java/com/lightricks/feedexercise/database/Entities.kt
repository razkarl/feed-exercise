package com.lightricks.feedexercise.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedItems")
// See data/Models.kt : FeedItem
data class FeedItemEntity(
    @PrimaryKey val id: String,
    @ColumnInfo val thumbnailUrl: String,
    @ColumnInfo val isPremium: Boolean
)