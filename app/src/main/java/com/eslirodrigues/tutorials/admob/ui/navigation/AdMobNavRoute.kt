package com.eslirodrigues.tutorials.admob.ui.navigation

sealed class AdMobNavRoute(val route: String) {
    data object AdScreen : AdMobNavRoute("ad_screen")
    data object AdSecondScreen : AdMobNavRoute("ad_second_screen")
}