package com.raj.navan.viewModel

import androidx.lifecycle.*
import com.raj.navan.repo.DataResult
import com.raj.navan.repo.NewsRepository
import com.raj.navan.repo.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val _searchString = MutableLiveData<String>("")
    private var _newsResponse = _searchString.switchMap { loadNews(it) }

    val mediatorLiveData = MediatorLiveData<UiState>().apply {
        addSource(_newsResponse) {
            value = it
        }
    }

    private suspend fun processData(newsResponse: NewsResponse): NewsResponse {
        newsResponse.response.docs.onEach { doc ->
            doc.isBookMarked = newsRepository.getBookmark(doc.web_url)
        }
        return newsResponse
    }

    fun doSearch(searchStr: String) {
        //
        if (searchStr.length > 3) {
            _searchString.value = searchStr
        }
    }

    private fun loadNews(searchStr: String): LiveData<UiState> {
        return liveData {
            if (searchStr.length > 3) {
                newsRepository.getNews(searchStr).collect() { response ->
                    when (response) {
                        is DataResult.Error -> emit(UiState.Error(response.exception))
                        is DataResult.Success -> emit(
                            UiState.NewsResponseSuccess(
                                processData(
                                    response.data
                                )
                            )
                        )
                    }
                }

            }
        }
    }

    fun saveBookmark(url: String, isBookmarked: Boolean) {
        viewModelScope.launch {
            newsRepository.saveBookMark(url, isBookmarked)
        }
    }
}