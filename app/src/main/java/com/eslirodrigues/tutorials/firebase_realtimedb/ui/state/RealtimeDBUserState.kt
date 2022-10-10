package com.eslirodrigues.tutorials.firebase_realtimedb.ui.state

import com.eslirodrigues.tutorials.firebase_realtimedb.data.model.RealtimeDBUser

data class RealtimeDBUserState(
    val data: List<RealtimeDBUser?>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)