package com.eslirodrigues.tutorials.navigation.ui.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.navigation.toRoute
import androidx.test.espresso.Espresso
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    companion object {
        const val NAVSECONDSCREEN_ARGS_ROUTE = "/{name}/{isOverEighteen}"
    }

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            TutorialNavGraph(navController = navController)
        }
    }

    @Test
    fun assert_IsMainScreenDestinationRouteRight() {
        val currentDestination = navController.currentBackStackEntry?.destination?.route

        Truth.assertThat(currentDestination)
            .isEqualTo(TutorialNavRoute.NavMainScreen::class.qualifiedName)
    }

    @Test
    fun assert_IsStartDestinationRouteRight() {
        val startDestination = navController.graph.startDestinationRoute
        val currentDestination = navController.currentBackStackEntry?.destination?.route

        Truth.assertThat(currentDestination).isEqualTo(startDestination)
    }

    @Test
    fun navigateToSecondScreen_Assert_IsSecondScreenDestinationRouteRight() {
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNodeWithText("SecondScreen").performClick()

        val currentDestination = navController.currentBackStackEntry?.destination?.route

        Truth.assertThat(currentDestination).isEqualTo(
            TutorialNavRoute.NavSecondScreen::class.qualifiedName?.plus(NAVSECONDSCREEN_ARGS_ROUTE)
        )
    }

    @Test
    fun navigateToSecondScreen_Assert_IsSecondScreenDestinationRouteTrue() {
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNodeWithText("SecondScreen").performClick()

        val hasNavSecondScreenRoute =
            navController.currentBackStackEntry?.destination?.hasRoute<TutorialNavRoute.NavSecondScreen>()

        Truth.assertThat(hasNavSecondScreenRoute).isEqualTo(true)
    }

    @Test
    fun navigateToSecondScreen_Assert_AreSecondScreenArgumentsCorrect() {
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNodeWithText("SecondScreen").performClick()

        val args = navController.currentBackStackEntry?.toRoute<TutorialNavRoute.NavSecondScreen>()
        val name = args?.name
        val isOverEighteen = args?.isOverEighteen

        Truth.assertThat(name).isEqualTo("Carl")
        Truth.assertThat(isOverEighteen).isEqualTo(true)
    }

    @Test
    fun navigateToSecondScreen_And_PressBackButton_Assert_IsMainScreenDestinationRouteRight() {
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNodeWithText("SecondScreen").performClick()

        Espresso.pressBack()

        val currentDestination = navController.currentBackStackEntry?.destination?.route

        Truth.assertThat(currentDestination)
            .isEqualTo(TutorialNavRoute.NavMainScreen::class.qualifiedName)
    }

    @Test
    fun navigateToThirdScreen_And_PopBackStackToSecondScreen_Assert_IsThirdScreenNotInBackStack() {
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNodeWithText("SecondScreen").performClick()
        composeTestRule.onNodeWithText("ThirdScreen").performClick()

        composeTestRule.onNodeWithText("SecondScreen").performClick()

        val isThirdScreenInBackStack = navController.backStack.any { backStack ->
            backStack.destination.route == TutorialNavRoute.NavThirdScreen::class.qualifiedName
        }

        Truth.assertThat(isThirdScreenInBackStack).isEqualTo(false)
    }
}











