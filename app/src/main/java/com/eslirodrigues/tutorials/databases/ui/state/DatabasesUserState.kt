package com.eslirodrigues.tutorials.databases.ui.state

import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser

data class DatabasesUserState(
    val data: List<DatabasesUser> = emptyList(),
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val snackbarMsg: String? = null
)
