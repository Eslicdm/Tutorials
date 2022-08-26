package com.eslirodrigues.tutorials.animated_navigation.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavGraphBuilder
import com.eslirodrigues.tutorials.animated_navigation.ui.screen.FirstAnimatedScreen
import com.eslirodrigues.tutorials.animated_navigation.ui.screen.SecondAnimatedScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavAnimatedGraph() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = NavAnimatedRoute.FirstScreen.route) {
        composableScale(route = NavAnimatedRoute.FirstScreen.route) {
            FirstAnimatedScreen(navController = navController)
        }
        composableScale(route = NavAnimatedRoute.SecondScreen.route) {
            SecondAnimatedScreen(navController = navController)
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

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableSlideVertically(
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
        enterTransition = { slideInVertically(tween(duration)) { enterOffset } },
        exitTransition = { slideOutVertically(tween(duration)){ exitOffset } },
        popEnterTransition = { slideInVertically(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutVertically(tween(duration)) { popExitOffset } },
        content = { screen() }
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableFade(
    route: String,
    duration: Int = 600, // 200 - 600
    enterAlpha: Float = 0f, // 1f - 0f
    exitAlpha: Float = 0f,
    popEnterAlpha: Float = 0f,
    popExitAlpha: Float = 0f,
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { fadeIn(tween(duration), enterAlpha) },
        exitTransition = { fadeOut(tween(duration), exitAlpha) },
        popEnterTransition = { fadeIn(tween(duration), popEnterAlpha) },
        popExitTransition = { fadeOut(tween(duration), popExitAlpha) },
        content = { screen() }
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableScale(
    route: String,
    duration: Int = 300, // 300 - 600 - 400
    enterScale: Float = 400f, // 400f - 0f - 100f
    exitScale: Float = 400f,
    popEnterScale: Float = 400f,
    popExitScale: Float = 400f,
    scaleEnterOrigin: Pair<Float, Float> = Pair(0.5f, 0.5f), // 0.5f, 0.5f - 1f, 1f - 0.5f, 1f
    scaleExitOrigin: Pair<Float, Float> = Pair(0.5f, 0.5f), // 0.5f, 0.5f - 1f, 1f - 0.5f, 1f
    scalePopEnterOrigin: Pair<Float, Float> = Pair(0.5f, 0.5f), // 0.5f, 0.5f - 1f, 1f - 0.5f, 1f
    scalePopExitOrigin: Pair<Float, Float> = Pair(0.5f, 0.5f), // 0.5f, 0.5f - 1f, 1f - 0.5f, 1f
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { scaleIn(tween(duration), enterScale, TransformOrigin(scaleEnterOrigin.first, scaleEnterOrigin.second)) },
        exitTransition = { scaleOut(tween(duration), exitScale, TransformOrigin(scaleExitOrigin.first, scaleExitOrigin.second)) },
        popEnterTransition = { scaleIn(tween(duration), popEnterScale, TransformOrigin(scalePopEnterOrigin.first, scalePopEnterOrigin.second)) },
        popExitTransition = { scaleOut(tween(duration), popExitScale, TransformOrigin(scalePopExitOrigin.first, scalePopExitOrigin.second)) },
        content = { screen() }
    )
}