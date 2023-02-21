package com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.state

import com.eslirodrigues.tutorials.state_stateflow_viewmodel.data.model.TutorialStateUser

data class UserState(
    val data: List<TutorialStateUser>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)