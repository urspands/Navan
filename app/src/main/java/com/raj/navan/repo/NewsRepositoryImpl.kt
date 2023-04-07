package com.raj.navan.repo

import android.util.Log
import com.raj.navan.repo.db.NewsDao
import com.raj.navan.repo.db.NewsEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val newsDao: NewsDao
) : NewsRepository {
//    private val _localCache = HashMap<String, NewsResponse>()
    override suspend fun getNews(searchString: String): Flow<DataResult<NewsResponse>> = flow {
        try {
            val localResponse = newsDao.getAllNews(searchString).first()
//            val localResponse = _localCache[searchString]
            localResponse?.let {
                Log.d(TAG, "getNews: value found in local cache for $searchString")
                emit(DataResult.Success(it.newsResponse))
            }

            delay(3000)
            val response = networkApi.getNewsFromServer(searchString)
//            _localCache[searchString] = response
            newsDao.insertNews(NewsEntity(searchString, response))
            emit(DataResult.Success(response))
        } catch (e: Exception) {
            emit(DataResult.Error(e))
        }
    }

    companion object {
        const val TAG = "NewsRepositoryImpl"
    }
}