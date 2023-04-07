package com.raj.navan.repo.db

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.raj.navan.repo.NewsResponse

class Converters {
    private val _gson = GsonBuilder().create()

    @TypeConverter
    fun fromNewsResponse(value: String): NewsResponse {
        return _gson.fromJson(value, NewsResponse::class.java)
    }

    @TypeConverter
    fun newsResponseToString(newsResponse: NewsResponse): String {
        return _gson.toJson(newsResponse)
    }
}