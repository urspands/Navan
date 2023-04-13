package com.raj.navan.viewModel

import androidx.lifecycle.*
import com.raj.navan.repo.DataResult
import com.raj.navan.repo.NewsRepository
import com.raj.navan.repo.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val _searchString = MutableLiveData<String>("")
    private var _newsResponse = _searchString.switchMap { loadNews(it) }
    private val _newsUiModel = _newsResponse.map {  }

    val mediatorLiveData = MediatorLiveData<UiState>().apply {
        addSource(_newsResponse) {
            value = it
        }
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
                when (val response = newsRepository.getNews(searchStr)) {
                    is DataResult.Error -> emit(UiState.Error(response.exception))
                    is DataResult.Success -> emit(UiState.NewsResponseSuccess(response.data))
                }
            }
        }
    }
}