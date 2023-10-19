package com.eslirodrigues.tutorials.firebase_auth.ui.screen.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.firebase_auth.ui.navigation.FirebaseAuthNavRoute
import com.eslirodrigues.tutorials.firebase_auth.ui.state.FirebaseLoginState
import com.eslirodrigues.tutorials.firebase_auth.ui.viewmodel.FirebaseAuthViewModel

@Composable
fun FirebaseAuthSignInScreen(
    navController: NavController,
    viewModel: FirebaseAuthViewModel = hiltViewModel()
) {
    var inputEmail by rememberSaveable { mutableStateOf("") }
    var inputPassword by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Sign In", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        TextField(value = inputEmail, onValueChange = { inputEmail = it } )
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(value = inputPassword, onValueChange = { inputPassword = it } )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = {
            if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty())
                viewModel.signInAuthUser(inputEmail, inputPassword)
        }) {
            when (viewModel.loginState.value) {
                is FirebaseLoginState.Success -> {
                    LaunchedEffect(key1 = Unit) {
                        navController.navigate(FirebaseAuthNavRoute.AuthMainScreen.route)
                    }
                }
                is FirebaseLoginState.Loading -> { CircularProgressIndicator(color = Color.White) }
                else -> { Text(text = "Submit") }
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { navController.navigate(FirebaseAuthNavRoute.AuthSignUp.route) }) {
            Text(text = "Sign up")
        }
    }
}