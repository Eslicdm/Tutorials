package com.eslirodrigues.tutorials.databases.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.databases.data.DatabasesUserResult
import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser
import com.eslirodrigues.tutorials.databases.data.repository.DatabasesUserRepository
import com.eslirodrigues.tutorials.databases.ui.state.DatabasesUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabasesUserViewModel @Inject constructor(
    private val repository: DatabasesUserRepository,
) : ViewModel() {

    private val _userState = MutableStateFlow(DatabasesUserState())
    val userState: StateFlow<DatabasesUserState> = _userState.asStateFlow()

    init {
        getAllUsers()
    }

    fun addUser(user: DatabasesUser) = viewModelScope.launch {
        repository.addUser(user).collect { result ->
            when (result) {
                is DatabasesUserResult.Success -> {}
                is DatabasesUserResult.Error -> {}
                is DatabasesUserResult.Loading -> {}
            }
        }
    }
    fun updateUser(user: DatabasesUser) = viewModelScope.launch {
        repository.updateUser(user).collect { result ->
            when (result) {
                is DatabasesUserResult.Success -> {}
                is DatabasesUserResult.Error -> {}
                is DatabasesUserResult.Loading -> {}
            }
        }
    }
    fun deleteUser(user: DatabasesUser) = viewModelScope.launch {
        repository.deleteUser(user).collect { result ->
            when (result) {
                is DatabasesUserResult.Success -> {}
                is DatabasesUserResult.Error -> {}
                is DatabasesUserResult.Loading -> {}
            }
        }
    }
    private fun getAllUsers() = viewModelScope.launch {
        repository.getAllUsers().collect { result ->
            when (result) {
                is DatabasesUserResult.Success -> {
                    _userState.update {
                        it.copy(data = result.data, isLoading = false, errorMsg = null)
                    }
                }
                is DatabasesUserResult.Error -> {
                    _userState.update {
                        it.copy(data = null, isLoading = false, errorMsg = result.exception.message)
                    }
                }
                is DatabasesUserResult.Loading -> {
                    _userState.update {
                        it.copy(data = null, isLoading = true, errorMsg = null)
                    }
                }
            }
        }
    }
}