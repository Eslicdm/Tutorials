package com.eslirodrigues.tutorials.firebase_firestore.data

sealed class FirestoreDBResult<out R> {
    data class Success<out T>(val data: T) : FirestoreDBResult<T>()
    data class Error(val exception: Throwable) : FirestoreDBResult<Nothing>()
    object Loading : FirestoreDBResult<Nothing>()
}