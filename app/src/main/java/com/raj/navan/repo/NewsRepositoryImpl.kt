package com.raj.navan.repo

import android.util.Log
import com.raj.navan.db.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val newsDatabase: NewsDatabase,
    private val localPreference: LocalPreference
) : NewsRepository {
    override suspend fun getNews(searchString: String): Flow<DataResult<NewsResponse>> = flow {
        try {
            val newsEntity = newsDatabase.getNewsDao().getNews(searchString).first()
            newsEntity?.let {
                emit(DataResult.Success(newsEntity.newsResponse))
                Log.d(TAG, "getNews: emitting from local cache")
            }
            val response = networkApi.getNewsFromServer(searchString)
            newsDatabase.getNewsDao().insert(NewsEntity(searchString, response))
            emit(DataResult.Success(response))
            Log.d(TAG, "getNews: emitting from server response.")
        } catch (e: Exception) {
            emit(DataResult.Error(e))
        }
    }

    override suspend fun saveBookMark(url: String, isBookmarked: Boolean) {
//        localPreference.saveBoolean(url, isBookmarked)
        newsDatabase.getBookmarkDao().saveBookMark(BookmarkEntity(url, isBookmarked))
    }

    override suspend fun getBookmark(url: String): Boolean {
        return newsDatabase.getBookmarkDao().getBookMark(url)?.isBookmarked ?: false
//        return localPreference.getBoolean(url)
    }

    companion object {
        const val TAG = "NewsRepositoryImpl"
    }
}