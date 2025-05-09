package com.eslirodrigues.tutorials.utils.navigation_bar.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.eslirodrigues.tutorials.utils.navigation_bar.ui.navigation.TutorialNavigationBarRoute

@Composable
fun TutorialNavigationBar(
    navController: NavController
) {
    val navList = listOf(
        TutorialNavigationBarRoute.Home,
        TutorialNavigationBarRoute.Image,
        TutorialNavigationBarRoute.Music
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        navList.forEach { item ->
            NavigationBarItem(
                selected = item.route == currentRoute,
                icon = {
                    Icon(
                        painter = painterResource(id = item.drawerIcon),
                        contentDescription = item.navName
                    )
                },
                label = { Text(text = item.navName) },
                alwaysShowLabel = true,
                onClick = {
                    if (currentRoute != item.route)
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { popUpTo(it) }
                            launchSingleTop = true
                        }
                }
            )
        }
    }
}