package com.raj.navan.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [NewsEntity::class, BookmarkEntity::class], version = 1,exportSchema = true
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
    abstract fun getBookmarkDao(): BookmarkDao
}