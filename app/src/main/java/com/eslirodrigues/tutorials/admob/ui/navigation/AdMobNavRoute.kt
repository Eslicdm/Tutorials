package com.eslirodrigues.tutorials.admob.ui.navigation

sealed class AdMobNavRoute(val route: String) {
    object AdScreen : AdMobNavRoute("ad_screen")
    object AdSecondScreen : AdMobNavRoute("ad_second_screen")
}