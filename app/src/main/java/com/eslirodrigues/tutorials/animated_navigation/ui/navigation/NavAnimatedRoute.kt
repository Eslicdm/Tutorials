package com.eslirodrigues.tutorials.animated_navigation.ui.navigation

sealed class NavAnimatedRoute(val route: String) {
    object FirstScreen : NavAnimatedRoute(route = "first_animated_screen")
    object SecondScreen : NavAnimatedRoute(route = "second_animated_screen")
}