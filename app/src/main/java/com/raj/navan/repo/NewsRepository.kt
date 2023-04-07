package com.raj.navan.repo

interface NewsRepository {
    suspend fun getNews(searchString: String):DataResult<NewsResponse>
}