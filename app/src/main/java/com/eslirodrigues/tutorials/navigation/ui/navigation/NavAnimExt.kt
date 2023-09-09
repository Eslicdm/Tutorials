package com.eslirodrigues.tutorials.navigation.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.composableSlideHorizontally(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    duration: Int = 400, // 1000 - 400
    enterOffset: Int = 1000, // 300 - 1000
    exitOffset: Int = -1000,
    popEnterOffset: Int = -1000,
    popExitOffset: Int = 1000,
    screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = { slideInHorizontally(tween(duration)) { enterOffset } },
        exitTransition = { slideOutHorizontally(tween(duration)){ exitOffset } },
        popEnterTransition = { slideInHorizontally(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutHorizontally(tween(duration)) { popExitOffset } },
        content = { screen(it) }
    )
}

fun NavGraphBuilder.composableSlideVertically(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    duration: Int = 400, // 1000 - 400
    enterOffset: Int = 1000, // 300 - 1000
    exitOffset: Int = -1000,
    popEnterOffset: Int = -1000,
    popExitOffset: Int = 1000,
    screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = { slideInVertically(tween(duration)) { enterOffset } },
        exitTransition = { slideOutVertically(tween(duration)){ exitOffset } },
        popEnterTransition = { slideInVertically(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutVertically(tween(duration)) { popExitOffset } },
        content = { screen(it) }
    )
}

fun NavGraphBuilder.composableFade(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    duration: Int = 600, // 200 - 600
    enterAlpha: Float = 0f, // 1f - 0f
    exitAlpha: Float = 0f,
    popEnterAlpha: Float = 0f,
    popExitAlpha: Float = 0f,
    screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = { fadeIn(tween(duration), enterAlpha) },
        exitTransition = { fadeOut(tween(duration), exitAlpha) },
        popEnterTransition = { fadeIn(tween(duration), popEnterAlpha) },
        popExitTransition = { fadeOut(tween(duration), popExitAlpha) },
        content = { screen(it) }
    )
}

fun NavGraphBuilder.composableScale(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    duration: Int = 300, // 300 - 600 - 400
    enterScale: Float = 400f, // 400f - 0f - 100f
    exitScale: Float = 400f,
    popEnterScale: Float = 400f,
    popExitScale: Float = 400f,
    scaleEnterOrigin: Pair<Float, Float> = Pair(0.5f, 0.5f), // 0.5f, 0.5f - 1f, 1f - 0.5f, 1f
    scaleExitOrigin: Pair<Float, Float> = Pair(0.5f, 0.5f), // 0.5f, 0.5f - 1f, 1f - 0.5f, 1f
    scalePopEnterOrigin: Pair<Float, Float> = Pair(0.5f, 0.5f), // 0.5f, 0.5f - 1f, 1f - 0.5f, 1f
    scalePopExitOrigin: Pair<Float, Float> = Pair(0.5f, 0.5f), // 0.5f, 0.5f - 1f, 1f - 0.5f, 1f
    screen: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = { scaleIn(tween(duration), enterScale, TransformOrigin(scaleEnterOrigin.first, scaleEnterOrigin.second)) },
        exitTransition = { scaleOut(tween(duration), exitScale, TransformOrigin(scaleExitOrigin.first, scaleExitOrigin.second)) },
        popEnterTransition = { scaleIn(tween(duration), popEnterScale, TransformOrigin(scalePopEnterOrigin.first, scalePopEnterOrigin.second)) },
        popExitTransition = { scaleOut(tween(duration), popExitScale, TransformOrigin(scalePopExitOrigin.first, scalePopExitOrigin.second)) },
        content = { screen(it) }
    )
}