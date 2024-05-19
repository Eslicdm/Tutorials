package com.eslirodrigues.tutorials.navigation.ui.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class TutorialNavRoute {
    @Serializable
    data object NavMainScreen : TutorialNavRoute()

    @Serializable
    data class NavSecondScreen(
        val name: String,
        val isOverEighteen: Boolean
    ) : TutorialNavRoute()

    @Serializable
    data object NavThirdScreen : TutorialNavRoute()
}
