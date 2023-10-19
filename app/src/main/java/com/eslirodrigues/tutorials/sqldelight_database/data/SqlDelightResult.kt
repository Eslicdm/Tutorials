package com.eslirodrigues.tutorials.sqldelight_database.data

sealed class SqlDelightResult<out R> {
    data class Success<out T>(val data: T): SqlDelightResult<T>()
    data class Error(val exception: Throwable): SqlDelightResult<Nothing>()
    data object Loading: SqlDelightResult<Nothing>()
}