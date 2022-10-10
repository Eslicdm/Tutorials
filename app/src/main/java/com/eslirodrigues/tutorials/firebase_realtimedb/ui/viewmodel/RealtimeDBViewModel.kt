package com.eslirodrigues.tutorials.firebase_realtimedb.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.firebase_realtimedb.data.model.RealtimeDBUser
import com.eslirodrigues.tutorials.firebase_realtimedb.data.repository.RealtimeDBRepository
import com.eslirodrigues.tutorials.firebase_realtimedb.data.util.RealtimeDBResult
import com.eslirodrigues.tutorials.firebase_realtimedb.ui.state.RealtimeDBUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealtimeDBViewModel @Inject constructor(
    private val repository: RealtimeDBRepository,
    @ApplicationContext private val context: Context,
) : ViewModel() {

    private val _userState = MutableStateFlow(RealtimeDBUserState())
    val userState: StateFlow<RealtimeDBUserState> = _userState.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        repository.getUser().collect { result ->
            when(result) {
                is RealtimeDBResult.Success -> {
                    _userState.update { it.copy(data = result.data, isLoading = false, errorMsg = null) }
                }
                is RealtimeDBResult.Error -> {
                    _userState.update {
                        it.copy(data = null, isLoading = false, errorMsg = result.exception.message)
                    }
                }
                is RealtimeDBResult.Loading -> {
                    _userState.update { it.copy(data = null, isLoading = true, errorMsg = null) }
                }
            }
        }
    }

    fun createUser(user: RealtimeDBUser) = viewModelScope.launch {
        repository.createUser(user).collect { result ->
            when(result) {
                is RealtimeDBResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is RealtimeDBResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is RealtimeDBResult.Loading -> {
                }
            }
        }
    }

    fun updateUser(user: RealtimeDBUser) = viewModelScope.launch {
        repository.updateUser(user).collect { result ->
            when(result) {
                is RealtimeDBResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is RealtimeDBResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is RealtimeDBResult.Loading -> {
                }
            }
        }
    }

    fun deleteUser(user: RealtimeDBUser) = viewModelScope.launch {
        repository.deleteUser(user).collect { result ->
            when(result) {
                is RealtimeDBResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is RealtimeDBResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is RealtimeDBResult.Loading -> {
                }
            }
        }
    }
}