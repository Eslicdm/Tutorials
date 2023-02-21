package com.eslirodrigues.tutorials.firebase_firestore.ui.state

import com.eslirodrigues.tutorials.firebase_firestore.data.model.FirestoreDBUser

data class FirestoreDBUserState(
    val data: List<FirestoreDBUser?>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)