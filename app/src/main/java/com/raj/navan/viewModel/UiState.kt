package com.raj.navan.viewModel

import com.raj.navan.repo.NewsResponse

sealed class UiState {
    object Loading : UiState()
    data class NewsResponseSuccess(val data: NewsResponse) : UiState()
    data class Error(val e: Exception) : UiState()

}
