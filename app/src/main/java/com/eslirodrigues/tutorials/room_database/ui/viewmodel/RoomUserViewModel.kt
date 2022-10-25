package com.eslirodrigues.tutorials.room_database.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.room_database.data.RoomResult
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
import com.eslirodrigues.tutorials.room_database.data.repository.RoomUserRepository
import com.eslirodrigues.tutorials.room_database.ui.state.RoomUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomUserViewModel @Inject constructor(
    private val repository: RoomUserRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _userState = MutableStateFlow(RoomUserState())
    val userState: StateFlow<RoomUserState> = _userState.asStateFlow()

    init {
        getAllUsers()
    }

    fun addUser(user: RoomUser) = viewModelScope.launch {
        repository.addUser(user).collect { result ->
            when (result) {
                is RoomResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Loading -> {
                }
            }
        }
    }
    fun updateUser(user: RoomUser) = viewModelScope.launch {
        repository.updateUser(user).collect { result ->
            when (result) {
                is RoomResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Loading -> {
                }
            }
        }
    }
    fun deleteUser(user: RoomUser) = viewModelScope.launch {
        repository.deleteUser(user).collect { result ->
            when (result) {
                is RoomResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is RoomResult.Loading -> {
                }
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