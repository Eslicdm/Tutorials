package com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.state

data class UserState(
    val data: List<String>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)