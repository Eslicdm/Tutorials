package com.eslirodrigues.tutorials.firebase_firestore.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.firebase_firestore.data.FirestoreDBResult
import com.eslirodrigues.tutorials.firebase_firestore.data.model.FirestoreDBUser
import com.eslirodrigues.tutorials.firebase_firestore.data.repository.FirestoreDBRepository
import com.eslirodrigues.tutorials.firebase_firestore.ui.state.FirestoreDBUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirestoreDBViewModel @Inject constructor(
    private val repository: FirestoreDBRepository,
) : ViewModel() {

    val userState: StateFlow<FirestoreDBUserState> = repository.getUsers().map { result ->
        when (result) {
            is FirestoreDBResult.Success -> FirestoreDBUserState(data = result.data)
            is FirestoreDBResult.Error -> FirestoreDBUserState(errorMsg = result.exception.message)
            is FirestoreDBResult.Loading -> FirestoreDBUserState(isLoading = true)
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = FirestoreDBUserState(isLoading = true),
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun createUser(user: FirestoreDBUser, context: Context) = viewModelScope.launch {
        repository.createUser(user).collect { result ->
            when (result) {
                is FirestoreDBResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is FirestoreDBResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                else -> { return@collect }
            }
        }
    }

    fun updateUser(user: FirestoreDBUser, context: Context) = viewModelScope.launch {
        repository.updateUser(user).collect { result ->
            when (result) {
                is FirestoreDBResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is FirestoreDBResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                else -> { return@collect }
            }
        }
    }

    fun deleteUser(user: FirestoreDBUser, context: Context) = viewModelScope.launch {
        repository.deleteUser(user).collect { result ->
            when (result) {
                is FirestoreDBResult.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }
                is FirestoreDBResult.Error -> {
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                else -> { return@collect }
            }
        }
    }
}