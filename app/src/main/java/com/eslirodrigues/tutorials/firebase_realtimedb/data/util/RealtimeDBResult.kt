package com.eslirodrigues.tutorials.firebase_realtimedb.data.util

sealed class RealtimeDBResult<out R> {
    data class Success<out T>(val data: T) : RealtimeDBResult<T>()
    data class Error(val exception: Throwable) : RealtimeDBResult<Nothing>()
    object Loading : RealtimeDBResult<Nothing>()
}