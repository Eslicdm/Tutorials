package com.eslirodrigues.tutorials.navigation.ui.navigation


sealed class NavRoute(val route: String) {
    data object NavMainScreen : NavRoute(route = "nav_main_screen")   //{name}/{isOverEighteen}
    data object NavSecondScreen : NavRoute(route = "nav_second_screen/{${ArgsKeys.NAME}}/{${ArgsKeys.IS_OVER_EIGHTEEN}}") {
        fun routeWithArgs(name: String, isOverEighteen: Boolean): String {
            return "nav_second_screen/$name/$isOverEighteen"
        }
    }
    data object NavThirdScreen : NavRoute(route = "nav_third_screen")
}
