package com.raj.navan.repo

import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(searchString: String): Flow<DataResult<NewsResponse>>

    suspend fun saveBookMark(url:String,isBookmarked:Boolean)

    suspend fun getBookmark(url: String):Boolean
}