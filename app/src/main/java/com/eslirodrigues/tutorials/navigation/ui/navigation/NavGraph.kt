package com.eslirodrigues.tutorials.navigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eslirodrigues.tutorials.navigation.ui.screen.NavMainScreen
import com.eslirodrigues.tutorials.navigation.ui.screen.SecondScreen
import com.eslirodrigues.tutorials.navigation.ui.screen.ThirdScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = NavRoute.NavMainScreen.route) { // "nav_main_screen"
        composableSlideHorizontally(route = NavRoute.NavMainScreen.route) {
            NavMainScreen { name, isOverEighteen ->
                navController.navigate(NavRoute.NavSecondScreen.routeWithArgs(name, isOverEighteen))
            }
        }
        composableSlideHorizontally(
            route = NavRoute.NavSecondScreen.route,
            arguments = listOf(
                navArgument(ArgsKeys.NAME) { type = NavType.StringType },
                navArgument(ArgsKeys.IS_OVER_EIGHTEEN) { type = NavType.BoolType }
            )
        ) {
            val name = it.arguments?.getString(ArgsKeys.NAME) ?: ""
            val isOverEighteen = it.arguments?.getBoolean(ArgsKeys.IS_OVER_EIGHTEEN) ?: false
            SecondScreen(
                onNavClick = { navController.navigate(NavRoute.NavThirdScreen.route) },
                onPopBackStackClick = { navController.popBackStack() },
                name = name,
                isOverEighteen = isOverEighteen
            )
        }
        composableSlideHorizontally(route = NavRoute.NavThirdScreen.route) {
            ThirdScreen(
                onNavClick = { navController.navigate(NavRoute.NavMainScreen.route) },
                onPopBackStackClick = {
                    navController.popBackStack(NavRoute.NavSecondScreen.route, inclusive = false)
                }
            )
        }
    }
}

