package com.raj.navan.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("Select * from newsEntity where search = :search")
    fun getNews(search: String): Flow<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(newsEntity: NewsEntity)
}