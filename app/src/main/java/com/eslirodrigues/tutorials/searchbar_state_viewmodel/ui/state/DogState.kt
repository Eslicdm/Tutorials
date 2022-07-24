package com.eslirodrigues.tutorials.searchbar_state_viewmodel.ui.state

data class DogState(
    val data: List<String>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)