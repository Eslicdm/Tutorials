package com.eslirodrigues.tutorials.firebase_auth.ui.state

sealed interface FirebaseLoginState {
    object Success : FirebaseLoginState
    object Error : FirebaseLoginState
    object Loading : FirebaseLoginState
    object Empty : FirebaseLoginState
}