package com.raj.navan.repo

import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(searchString: String): Flow<DataResult<NewsResponse>>
}