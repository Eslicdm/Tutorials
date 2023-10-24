package com.eslirodrigues.tutorials.window_manager.ui.navigation

import android.app.Activity
import android.util.Log
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.window.layout.FoldingFeature
import com.eslirodrigues.tutorials.window_manager.ui.screen.TutorialWindowManagerAndDescriptionScreen
import com.eslirodrigues.tutorials.window_manager.ui.screen.TutorialWindowManagerDescriptionScreen
import com.eslirodrigues.tutorials.window_manager.ui.screen.TutorialWindowManagerScreen
import com.google.accompanist.adaptive.calculateDisplayFeatures

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun TutorialWindowManagerNavGraph(
    activity: Activity,
    navController: NavHostController = rememberNavController()
) {
    val windowSizeClass = calculateWindowSizeClass(activity)
    val widthSizeClass by rememberUpdatedState(windowSizeClass.widthSizeClass)
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    isExpanded = when (widthSizeClass) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> false
        WindowWidthSizeClass.Expanded -> true
        else -> true
    }

    val displayFeatures = calculateDisplayFeatures(activity)
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()
    var isTableTopPosture by rememberSaveable { mutableStateOf(false) }
    var isBookPosture by rememberSaveable { mutableStateOf(false) }
    isTableTopPosture = foldingFeature?.state == FoldingFeature.State.HALF_OPENED &&
            foldingFeature.orientation == FoldingFeature.Orientation.HORIZONTAL // divide-top-bottom
    isBookPosture = foldingFeature?.state == FoldingFeature.State.HALF_OPENED &&
            foldingFeature.orientation == FoldingFeature.Orientation.VERTICAL // divide-left-right
    Log.d("FoldablePosture", "TableTop: $isTableTopPosture -- Book: $isBookPosture ")


    NavHost(navController = navController, startDestination = "main_screen") {
        composable(route = "main_screen") {
            if (!isExpanded) {
                TutorialWindowManagerScreen(
                    onItemClick = { name, description ->
                        navController.navigate("description_screen/$name/$description")
                    }
                )
            } else { TutorialWindowManagerAndDescriptionScreen() }
        }
        composable(
            route = "description_screen/{key_name}/{key_description}",
            arguments = listOf(
                navArgument("key_name") { type = NavType.StringType },
                navArgument("key_description") { type = NavType.StringType }
            )
        ) {
            val name = it.arguments?.getString("key_name") ?: ""
            val description = it.arguments?.getString("key_description") ?: ""
            TutorialWindowManagerDescriptionScreen(name = name, description = description)
        }
    }
}

