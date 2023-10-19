package com.eslirodrigues.tutorials.firebase_auth.ui.state

sealed interface FirebaseLoginState {
    data object Success : FirebaseLoginState
    data object Error : FirebaseLoginState
    data object Loading : FirebaseLoginState
    data object Empty : FirebaseLoginState
}