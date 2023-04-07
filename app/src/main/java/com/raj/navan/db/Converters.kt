package com.raj.navan.db

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.raj.navan.repo.NewsResponse

class Converters {
    private val _gson = GsonBuilder().create()

    @TypeConverter
    fun fromNewsResponseToString(newsResponse: NewsResponse): String {
        return _gson.toJson(newsResponse)
    }

    @TypeConverter
    fun fromStringToNewsResponse(news: String): NewsResponse {
        return _gson.fromJson(news, NewsResponse::class.java)
    }
}