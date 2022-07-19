package com.eslirodrigues.tutorials.ui.state

data class UserState(
    val data: List<String>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)