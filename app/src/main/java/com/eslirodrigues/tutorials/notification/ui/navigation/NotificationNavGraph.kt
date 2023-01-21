package com.eslirodrigues.tutorials.notification.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.eslirodrigues.tutorials.notification.ui.navigation.TutorialNotificationConstants.NOTIFICATION_RESPONSE
import com.eslirodrigues.tutorials.notification.ui.navigation.TutorialNotificationConstants.NOTIFICATION_URI
import com.eslirodrigues.tutorials.notification.ui.screen.NotificationResponseScreen
import com.eslirodrigues.tutorials.notification.ui.screen.NotificationScreen

@Composable
fun NotificationNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NotificationNavRoute.NotificationScreen.route
    ) {
        composable(route = NotificationNavRoute.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(
            route = NotificationNavRoute.NotificationResponseScreen.route,
            arguments = listOf(
                navArgument(NOTIFICATION_RESPONSE) { type = NavType.StringType }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "$NOTIFICATION_URI/$NOTIFICATION_RESPONSE={$NOTIFICATION_RESPONSE}"
                }
            )
        ) {
            val response = it.arguments?.getString(NOTIFICATION_RESPONSE) ?: ""
            NotificationResponseScreen(response)
        }
    }
}