package com.eslirodrigues.tutorials.databases.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.databases.data.DatabasesUserResult
import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser
import com.eslirodrigues.tutorials.databases.data.repository.DatabasesUserRepository
import com.eslirodrigues.tutorials.databases.ui.event.DatabasesUserEvent
import com.eslirodrigues.tutorials.databases.ui.state.DatabasesUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabasesUserViewModel @Inject constructor(
    private val repository: DatabasesUserRepository,
) : ViewModel() {

    private val _userState = MutableStateFlow(DatabasesUserState())
    val userState: StateFlow<DatabasesUserState> =
        combine(_userState, repository.getAllUsers()) { local, repository ->
            when (repository) {
                is DatabasesUserResult.Success -> {
                    if (local.data.isNotEmpty()) {
                        val combinedList = listOf(repository.data, local.data).flatten()
                        DatabasesUserState(data = combinedList)
                    } else {
                        DatabasesUserState(data = repository.data)
                    }
                }
                is DatabasesUserResult.Error -> {
                    DatabasesUserState(errorMsg = repository.exception.message)
                }
                is DatabasesUserResult.Loading -> DatabasesUserState(isLoading = true)
            }
        }.stateIn(
                scope = viewModelScope,
                initialValue = DatabasesUserState(isLoading = true),
                started = SharingStarted.WhileSubscribed(5000)
        )

    private fun handleDatabasesUserEvents(result: DatabasesUserResult<String>) {
        when (result) {
            is DatabasesUserResult.Success -> {
                _userState.update { it.copy(
                    snackbarMsg = result.data,
                    isLoading = false,
                    errorMsg = null
                ) }
            }
            is DatabasesUserResult.Error -> {
                _userState.update { it.copy(
                    isLoading = false,
                    errorMsg = result.exception.message
                ) }
            }
            is DatabasesUserResult.Loading -> {
                _userState.update { it.copy(
                    isLoading = true,
                    errorMsg = null
                ) }
            }
        }
    }

    fun onEvent(event: DatabasesUserEvent) {
        when (event) {
            is DatabasesUserEvent.AddUser -> viewModelScope.launch {
                repository.addUser(event.user).collect { result ->
                    handleDatabasesUserEvents(result)
                }
            }
            is DatabasesUserEvent.UpdateUser -> viewModelScope.launch {
                repository.updateUser(event.user).collect { result ->
                    handleDatabasesUserEvents(result)
                }
            }
            is DatabasesUserEvent.DeleteUser -> viewModelScope.launch {
                repository.deleteUser(event.user).collect { result ->
                    handleDatabasesUserEvents(result)
                }
            }
        }
    }
}

