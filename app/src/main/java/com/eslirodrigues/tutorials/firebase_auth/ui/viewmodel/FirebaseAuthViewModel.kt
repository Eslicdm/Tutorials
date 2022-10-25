package com.eslirodrigues.tutorials.firebase_auth.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.firebase_auth.auth.FirebaseAuthUser
import com.eslirodrigues.tutorials.firebase_auth.auth.util.FirebaseAuthResult
import com.eslirodrigues.tutorials.firebase_auth.ui.state.FirebaseAuthState
import com.eslirodrigues.tutorials.firebase_auth.ui.state.FirebaseLoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuthUser,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _userState = MutableStateFlow(FirebaseAuthState())
    val userState: StateFlow<FirebaseAuthState> = _userState.asStateFlow()

    val loginState: MutableState<FirebaseLoginState> = mutableStateOf(FirebaseLoginState.Empty)

    init {
        viewModelScope.launch {
            _userState.update { it.copy(
                authUser = firebaseAuth.getUser().firstOrNull(), isLoading = false, errorMsg = null)
            }
        }
    }

    fun signUpAuthUser(email: String, password: String) = viewModelScope.launch {
        firebaseAuth.signUpAuthUser(email, password).collect { result ->
            when(result) {
                is FirebaseAuthResult.Success -> {
                    loginState.value = FirebaseLoginState.Success
                    Toast.makeText(context, "sign up", Toast.LENGTH_SHORT).show()
                }
                is FirebaseAuthResult.Error -> {
                    loginState.value = FirebaseLoginState.Error
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is FirebaseAuthResult.Loading -> {
                    loginState.value = FirebaseLoginState.Loading
                }
            }
        }
    }

    fun signInAuthUser(email: String, password: String) = viewModelScope.launch {
        firebaseAuth.signInAuthUser(email, password).collect { result ->
            when(result) {
                is FirebaseAuthResult.Success -> {
                    loginState.value = FirebaseLoginState.Success
                    Toast.makeText(context, "sign in", Toast.LENGTH_SHORT).show()
                }
                is FirebaseAuthResult.Error -> {
                    loginState.value = FirebaseLoginState.Error
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is FirebaseAuthResult.Loading -> {
                    loginState.value = FirebaseLoginState.Loading
                }
            }
        }
    }

    fun signOutAuthUser() = viewModelScope.launch {
        firebaseAuth.signOutAuthUser().collect { result ->
            when(result) {
                is FirebaseAuthResult.Success -> {
                    loginState.value = FirebaseLoginState.Success
                    Toast.makeText(context, "log out", Toast.LENGTH_SHORT).show()
                }
                is FirebaseAuthResult.Error -> {
                    loginState.value = FirebaseLoginState.Error
                    Toast.makeText(context, result.exception.message, Toast.LENGTH_SHORT).show()
                }
                is FirebaseAuthResult.Loading -> {
                    loginState.value = FirebaseLoginState.Loading
                }
            }
        }
    }
}