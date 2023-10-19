package com.eslirodrigues.tutorials.firebase_auth.auth.util

sealed class FirebaseAuthResult<out R> {
    data class Success<out T>(val data: T) : FirebaseAuthResult<T>()
    data class Error(val exception: Throwable) : FirebaseAuthResult<Nothing>()
    data object Loading : FirebaseAuthResult<Nothing>()
}