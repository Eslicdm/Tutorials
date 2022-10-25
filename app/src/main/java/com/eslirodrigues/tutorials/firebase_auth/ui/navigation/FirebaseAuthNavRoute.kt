package com.eslirodrigues.tutorials.firebase_auth.ui.navigation

sealed class FirebaseAuthNavRoute(val route: String) {
    object AuthMainScreen : FirebaseAuthNavRoute(route = "nav_auth_main_screen")
    object AuthSignUp : FirebaseAuthNavRoute(route = "nav_auth_sign_up_screen")
    object AuthSignIn : FirebaseAuthNavRoute(route = "nav_auth_sign_in_screen")
}