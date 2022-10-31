package com.eslirodrigues.tutorials.navigation_bar.ui.navigation

import androidx.annotation.DrawableRes
import com.eslirodrigues.tutorials.R

sealed class TutorialNavigationBarRoute(
    val route: String, val navName: String, @DrawableRes val drawerIcon: Int,
) {
    object Home : TutorialNavigationBarRoute(
        route = "nav_home", navName = "Home", drawerIcon = R.drawable.ic_home
    )
    object Image : TutorialNavigationBarRoute(
        route = "nav_image", navName = "Image", drawerIcon = R.drawable.ic_image
    )
    object Music : TutorialNavigationBarRoute(
        route = "nav_music", navName = "Music", drawerIcon = R.drawable.ic_music_note
    )
}