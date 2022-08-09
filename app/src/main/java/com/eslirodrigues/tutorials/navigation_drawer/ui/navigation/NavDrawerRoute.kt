package com.eslirodrigues.tutorials.navigation_drawer.ui.navigation

import androidx.annotation.DrawableRes
import com.eslirodrigues.tutorials.R

sealed class NavDrawerRoute(val route: String, val drawerName: String, @DrawableRes val drawerIcon: Int) {
    object NavDrawerScreen : NavDrawerRoute(
        route = "nav_drawer_screen", drawerName = "Drawer", drawerIcon = R.drawable.ic_home
    )
    object SecondDrawerScreen : NavDrawerRoute(
        route = "second_drawer_screen", drawerName = "Second", drawerIcon = R.drawable.ic_headset
    )
}
