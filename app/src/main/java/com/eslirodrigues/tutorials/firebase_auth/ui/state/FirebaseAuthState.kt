package com.eslirodrigues.tutorials.firebase_auth.ui.state

import com.google.firebase.auth.FirebaseUser

data class FirebaseAuthState(
    val authUser: FirebaseUser? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
