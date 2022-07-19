package com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.state.UserState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        _userState.update { it.copy(isLoading = true) }
        delay(2000L)

        val userList = listOf("Karen", "Josh", "Michael", "Batman")
        _userState.update { it.copy(isLoading = false, data = userList) }
        delay(2000L)

        _userState.update { it.copy(isLoading = true) }
        delay(1000L)

        _userState.update { it.copy(isLoading = false, data = null, errorMsg = "Not Found") }
    }

}