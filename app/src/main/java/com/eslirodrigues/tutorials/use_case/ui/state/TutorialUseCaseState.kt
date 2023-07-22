package com.eslirodrigues.tutorials.use_case.ui.state

data class TutorialUseCaseState(
    val data: List<String>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)