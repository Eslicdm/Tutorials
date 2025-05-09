package com.eslirodrigues.tutorials.animations.ui.screen

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import com.eslirodrigues.tutorials.animations.AnimatedContent
import org.junit.Rule
import org.junit.Test

class TutorialAnimationsTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun assertTheAnimationValueIsEqual() {
        composeRule.mainClock.autoAdvance = false

        composeRule.setContent {
            AnimatedContent()
        }
        composeRule.onNode(hasClickAction()).performClick()

        composeRule.mainClock.advanceTimeBy(1000L)

        composeRule.onNode(hasClickAction()).assertTextEquals("1")
    }
}