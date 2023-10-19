package com.eslirodrigues.tutorials.retrofit_api.data

sealed class RetrofitApiResult<out R> {
    data class Success<out T>(val data: T) : RetrofitApiResult<T>()
    data class Error(val exception: Throwable) : RetrofitApiResult<Nothing>()
    data object Loading : RetrofitApiResult<Nothing>()
}