package com.eslirodrigues.tutorials.firebase_auth.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.firebase_auth.ui.navigation.FirebaseAuthNavRoute
import com.eslirodrigues.tutorials.firebase_auth.ui.state.FirebaseLoginState
import com.eslirodrigues.tutorials.firebase_auth.ui.viewmodel.FirebaseAuthViewModel

@Composable
fun FirebaseAuthMainScreen(
    navController: NavController,
    viewModel: FirebaseAuthViewModel = hiltViewModel(),
) {
    val userState by viewModel.userState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            userState.isLoading -> { CircularProgressIndicator() }
            userState.authUser == null -> {
                CircularProgressIndicator()
            }
            userState.authUser != null -> {
                Text(text = userState.authUser?.email ?: "")
                Button(onClick = {
                    viewModel.signOutAuthUser()
                }) {
                    when (viewModel.loginState.value) {
                        is FirebaseLoginState.Success -> {
                            LaunchedEffect(key1 = Unit) {
                                navController.navigate(FirebaseAuthNavRoute.AuthSignIn.route)
                            }
                        }
                        is FirebaseLoginState.Loading -> { CircularProgressIndicator(color = Color.White) }
                        else -> { Text(text = "Log out") }
                    }
                }
            }
        }
    }
}