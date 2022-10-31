package com.eslirodrigues.tutorials.navigation_bar.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.tutorials.navigation_bar.ui.screen.TutorialNavBarHomeScreen
import com.eslirodrigues.tutorials.navigation_bar.ui.screen.TutorialNavBarImageScreen
import com.eslirodrigues.tutorials.navigation_bar.ui.screen.TutorialNavBarMusicScreen

@Composable
fun TutorialNavigationBarNavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TutorialNavigationBarRoute.Home.route) {
        composable(TutorialNavigationBarRoute.Home.route) { TutorialNavBarHomeScreen(navController) }
        composable(TutorialNavigationBarRoute.Image.route) { TutorialNavBarImageScreen(navController) }
        composable(TutorialNavigationBarRoute.Music.route) { TutorialNavBarMusicScreen(navController) }
    }

}