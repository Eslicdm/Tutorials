package com.eslirodrigues.tutorials.room_database.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.room_database.data.RoomResult
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
import com.eslirodrigues.tutorials.room_database.data.repository.RoomUserRepository
import com.eslirodrigues.tutorials.room_database.ui.state.RoomUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomUserViewModel @Inject constructor(
    private val repository: RoomUserRepository,
) : ViewModel() {

    private val _userState = MutableStateFlow(RoomUserState())
    val userState: StateFlow<RoomUserState> = _userState.asStateFlow()

    init {
        getAllUsers()
    }

    fun addUser(user: RoomUser) = viewModelScope.launch {
        repository.addUser(user).collect { result ->
            when (result) {
                is RoomResult.Success -> {}
                is RoomResult.Error -> {}
                is RoomResult.Loading -> {}
            }
        }
    }
    fun updateUser(user: RoomUser) = viewModelScope.launch {
        repository.updateUser(user).collect { result ->
            when (result) {
                is RoomResult.Success -> {}
                is RoomResult.Error -> {}
                is RoomResult.Loading -> {}
            }
        }
    }
    fun deleteUser(user: RoomUser) = viewModelScope.launch {
        repository.deleteUser(user).collect { result ->
            when (result) {
                is RoomResult.Success -> {}
                is RoomResult.Error -> {}
                is RoomResult.Loading -> {}
            }
        }
    }
    private fun getAllUsers() = viewModelScope.launch {
        repository.getAllUsers().collect { result ->
            when (result) {
                is RoomResult.Success -> {
                    _userState.update {
                        it.copy(data = result.data, isLoading = false, errorMsg = null)
                    }
                }
                is RoomResult.Error -> {
                    _userState.update {
                        it.copy(data = null, isLoading = false, errorMsg = result.exception.message)
                    }
                }
                is RoomResult.Loading -> {
                    _userState.update {
                        it.copy(data = null, isLoading = true, errorMsg = null)
                    }
                }
            }
        }
    }
}