package com.raj.navan.repo

import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val networkApi: NetworkApi) : NewsRepository {
    override suspend fun getNews(searchString: String): DataResult<NewsResponse> {
        return try {
            val response = networkApi.getNewsFromServer(searchString)
            DataResult.Success(response)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }
}