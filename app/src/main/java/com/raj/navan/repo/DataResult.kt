package com.raj.navan.repo

sealed class DataResult<out D> {
    data class Success<S>(val data: S) : DataResult<S>()
    data class Error(val exception: java.lang.Exception) : DataResult<Nothing>()
}
