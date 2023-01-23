package com.eslirodrigues.tutorials.sqldelight_database.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.sqldelight_database.data.SqlDelightResult
import com.eslirodrigues.tutorials.sqldelight_database.data.repository.SqlDelightUserRepository
import com.eslirodrigues.tutorials.sqldelight_database.ui.state.SqlDelightUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tutorialsqldelightdb.SqldelightUserEntity
import javax.inject.Inject

@HiltViewModel
class SqlDelightViewModel @Inject constructor(
    private val repository: SqlDelightUserRepository
) : ViewModel() {

    private val _userState = MutableStateFlow(SqlDelightUserState())
    val userState: StateFlow<SqlDelightUserState> = _userState.asStateFlow()

    init {
        getAllUsers()
    }

    fun addUser(userId: Long?, userName: String, context: Context) = viewModelScope.launch {
        repository.addUser(userId, userName).collect { result ->
            when (result) {
                is SqlDelightResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is SqlDelightResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is SqlDelightResult.Loading -> {
                }
            }
        }
    }

    fun updateUser(user: SqldelightUserEntity, context: Context)= viewModelScope.launch {
        repository.updateUser(user).collect { result ->
            when (result) {
                is SqlDelightResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is SqlDelightResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is SqlDelightResult.Loading -> {
                }
            }
        }
    }

    fun deleteUser(user: SqldelightUserEntity, context: Context) = viewModelScope.launch {
        repository.deleteUser(user).collect { result ->
            when (result) {
                is SqlDelightResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is SqlDelightResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is SqlDelightResult.Loading -> {
                }
            }
        }
    }

    fun getAllUsers() = viewModelScope.launch {
        repository.getAllUsers().collect { result ->
            when (result) {
                is SqlDelightResult.Success -> {
                    _userState.update {
                        it.copy(data = result.data, isLoading = false, errorMsg = null)
                    }
                }
                is SqlDelightResult.Error -> {
                    _userState.update {
                        it.copy(data = null, isLoading = false, errorMsg = result.exception.message)
                    }
                }
                is SqlDelightResult.Loading -> {
                    _userState.update {
                        it.copy(data = null, isLoading = true, errorMsg = null)
                    }
                }
            }
        }
    }
}