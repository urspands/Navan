package com.raj.navan.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBookMark(bookmarkEntity: BookmarkEntity)

    @Query("Select * From bookmarkEntity where url = :url")
    suspend fun getBookMark(url: String): BookmarkEntity?
}