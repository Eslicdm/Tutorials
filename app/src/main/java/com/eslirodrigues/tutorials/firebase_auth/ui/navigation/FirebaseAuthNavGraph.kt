package com.eslirodrigues.tutorials.firebase_auth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.tutorials.firebase_auth.ui.screen.FirebaseAuthMainScreen
import com.eslirodrigues.tutorials.firebase_auth.ui.screen.auth.FirebaseAuthSignInScreen
import com.eslirodrigues.tutorials.firebase_auth.ui.screen.auth.FirebaseAuthSignUpScreen
import com.eslirodrigues.tutorials.firebase_auth.ui.viewmodel.FirebaseAuthViewModel

@Composable
fun FirebaseAuthNavGraph(
    viewModel: FirebaseAuthViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val userState by viewModel.userState.collectAsState()

    val startDestination = if (userState.authUser == null) {
        FirebaseAuthNavRoute.AuthSignIn.route
    } else {
        FirebaseAuthNavRoute.AuthMainScreen.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(FirebaseAuthNavRoute.AuthMainScreen.route) { FirebaseAuthMainScreen(navController) }
        composable(FirebaseAuthNavRoute.AuthSignUp.route) { FirebaseAuthSignUpScreen(navController) }
        composable(FirebaseAuthNavRoute.AuthSignIn.route) { FirebaseAuthSignInScreen(navController) }
    }
}