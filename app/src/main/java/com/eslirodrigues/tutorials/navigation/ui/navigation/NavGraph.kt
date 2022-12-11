package com.eslirodrigues.tutorials.navigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eslirodrigues.tutorials.navigation.ui.screen.NavMainScreen
import com.eslirodrigues.tutorials.navigation.ui.screen.SecondScreen
import com.eslirodrigues.tutorials.navigation.ui.screen.ThirdScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = NavRoute.NavMainScreen.route) { // "nav_main_screen"
        composable(route = NavRoute.NavMainScreen.route) { NavMainScreen(navController) }
        composable(
            route = NavRoute.NavSecondScreen.route, // second_screen/{name}/{isOverEighteen}
            arguments = listOf(
                navArgument(ArgsKeys.NAME) { // const NAME = name
                    type = NavType.StringType
                },
                navArgument(ArgsKeys.IS_OVER_EIGHTEEN) { type = NavType.BoolType }
                // const IS_OVER_EIGHTEEN = isOverEighteen
            )
        ) {
            val name = it.arguments?.getString(ArgsKeys.NAME) ?: "" // const NAME = name
            val isOverEighteen = it.arguments?.getBoolean(ArgsKeys.IS_OVER_EIGHTEEN) ?: false
                                    // const IS_OVER_EIGHTEEN = isOverEighteen
            SecondScreen(navController, name, isOverEighteen)
        }
        composable(route = NavRoute.NavThirdScreen.route) { ThirdScreen(navController = navController) }
    }
}