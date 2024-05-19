package com.eslirodrigues.tutorials.navigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.eslirodrigues.tutorials.navigation.ui.screen.TutorialNavMainScreen
import com.eslirodrigues.tutorials.navigation.ui.screen.TutorialNavSecondScreen
import com.eslirodrigues.tutorials.navigation.ui.screen.TutorialNavThirdScreen

@Composable
fun TutorialNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = TutorialNavRoute.NavMainScreen) {
        composableSlideHorizontally<TutorialNavRoute.NavMainScreen> {
            TutorialNavMainScreen { name, isOverEighteen ->
                navController.navigate(TutorialNavRoute.NavSecondScreen(name, isOverEighteen))
            }
        }
        composableSlideHorizontally<TutorialNavRoute.NavSecondScreen> {
            val args = it.toRoute<TutorialNavRoute.NavSecondScreen>()
            TutorialNavSecondScreen(
                onNavClick = { navController.navigate(TutorialNavRoute.NavThirdScreen) },
                onPopBackStackClick = { navController.popBackStack() },
                name = args.name,
                isOverEighteen = args.isOverEighteen
            )
        }
        composableSlideHorizontally<TutorialNavRoute.NavThirdScreen> {
            TutorialNavThirdScreen(
                onNavClick = { navController.navigate(TutorialNavRoute.NavMainScreen) },
                onPopBackStackClick = {
                    navController.popBackStack<TutorialNavRoute.NavSecondScreen>(inclusive = false)
                }
            )
        }
    }
}

