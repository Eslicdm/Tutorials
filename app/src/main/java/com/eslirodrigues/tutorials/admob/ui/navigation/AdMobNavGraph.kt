package com.eslirodrigues.tutorials.admob.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.whenCreated
import androidx.navigation.NavGraphBuilder
import com.eslirodrigues.tutorials.admob.ui.screen.AdScreen
import com.eslirodrigues.tutorials.admob.ui.screen.AdSecondScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AdMobNavGraph(showAd: () -> Unit) {
    val navController = rememberAnimatedNavController()
    var adCount by rememberSaveable { mutableStateOf(0) }

    AnimatedNavHost(navController = navController, startDestination = AdMobNavRoute.AdScreen.route) {
        composableSlideHorizontally(route = AdMobNavRoute.AdScreen.route) {
            AdScreen(navController = navController)
        }
        composableSlideHorizontally(route = AdMobNavRoute.AdSecondScreen.route) {
            val currentBackStackEntry = navController.currentBackStackEntry
             LaunchedEffect(Unit){
                currentBackStackEntry?.whenCreated {
                    if (adCount % 2 == 1) showAd()
                    adCount++
                }
            }
            AdSecondScreen(navController = navController)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableSlideHorizontally(
    route: String,
    duration: Int = 400, // 1000 - 400
    enterOffset: Int = 1000, // 300 - 1000
    exitOffset: Int = -1000,
    popEnterOffset: Int = -1000,
    popExitOffset: Int = 1000,
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideInHorizontally(tween(duration)) { enterOffset } },
        exitTransition = { slideOutHorizontally(tween(duration)){ exitOffset } },
        popEnterTransition = { slideInHorizontally(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutHorizontally(tween(duration)) { popExitOffset } },
        content = { screen() }
    )
}