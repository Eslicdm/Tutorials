package com.eslirodrigues.tutorials.ktor_client.data

sealed class TutorialKtorClientResult<out R> {
    data class Success<out T>(val data: T) : TutorialKtorClientResult<T>()
    data class Error(val exception: Throwable) : TutorialKtorClientResult<Nothing>()
    data object Loading : TutorialKtorClientResult<Nothing>()
}