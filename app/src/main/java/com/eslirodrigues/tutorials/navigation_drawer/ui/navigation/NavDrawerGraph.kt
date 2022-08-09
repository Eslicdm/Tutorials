package com.eslirodrigues.tutorials.navigation_drawer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.tutorials.navigation_drawer.ui.screen.NavDrawerScreen
import com.eslirodrigues.tutorials.navigation_drawer.ui.screen.SecondDrawerScreen

@Composable
fun NavDrawerGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavDrawerRoute.NavDrawerScreen.route) {
        composable(route = NavDrawerRoute.NavDrawerScreen.route) { NavDrawerScreen(navController) }
        composable(route = NavDrawerRoute.SecondDrawerScreen.route) { SecondDrawerScreen(navController = navController) }
    }
}