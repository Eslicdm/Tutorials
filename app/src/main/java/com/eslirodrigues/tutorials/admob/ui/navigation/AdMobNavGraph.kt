package com.eslirodrigues.tutorials.admob.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.withCreated
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.tutorials.admob.ui.screen.AdScreen
import com.eslirodrigues.tutorials.admob.ui.screen.AdSecondScreen

@Composable
fun AdMobNavGraph(showAd: () -> Unit) {
    val navController = rememberNavController()
    var adCount by rememberSaveable { mutableIntStateOf(0) }

    NavHost(navController = navController, startDestination = AdMobNavRoute.AdScreen.route) {
        composableSlideHorizontallyAdMob(route = AdMobNavRoute.AdScreen.route) {
            AdScreen { navController.navigate(AdMobNavRoute.AdSecondScreen.route) }
        }
        composableSlideHorizontallyAdMob(route = AdMobNavRoute.AdSecondScreen.route) {
            val currentBackStackEntry = navController.currentBackStackEntry
             LaunchedEffect(Unit){
                currentBackStackEntry?.withCreated {
                    if (adCount % 2 == 1) showAd()
                    adCount++
                }
            }
            AdSecondScreen { navController.popBackStack() }
        }
    }
}

fun NavGraphBuilder.composableSlideHorizontallyAdMob(
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