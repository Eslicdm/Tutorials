package com.eslirodrigues.tutorials.databases.data

sealed class DatabasesUserResult<out R> {
    data class Success<out T>(val data: T): DatabasesUserResult<T>()
    data class Error(val exception: Throwable): DatabasesUserResult<Nothing>()
    data object Loading: DatabasesUserResult<Nothing>()
}