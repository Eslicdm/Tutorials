package com.eslirodrigues.tutorials.utils.searchbar_state_viewmodel

data class DogState(
    val data: List<String>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)