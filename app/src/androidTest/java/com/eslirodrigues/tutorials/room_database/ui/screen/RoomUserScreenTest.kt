package com.eslirodrigues.tutorials.room_database.ui.screen

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.eslirodrigues.tutorials.MainActivity
import org.junit.*

class RoomUserScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.activity.setContent { RoomUserScreen() }
    }

    @After
    fun close() {
        composeTestRule.onNodeWithContentDescription("Delete").performClick()
    }

    @Test
    fun addUser_Assert_IsUserDisplayed() {
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNodeWithText("Submit").performClick()

        composeTestRule.onAllNodesWithTag("ROOM_USER_ITEM").assertAny(hasText("Name: Carl"))
    }

    @Test
    fun updateUserName_Assert_IsUpdatedUserDisplayed() {
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNodeWithText("Submit").performClick()
        composeTestRule.onAllNodesWithTag("ROOM_USER_ITEM").onFirst().performClick()

        composeTestRule.onNodeWithTag("UPDATE_TEXT_FIELD").performTextInput("Mike")
        composeTestRule.onNodeWithText("Update").performClick()

        composeTestRule.onAllNodesWithTag("ROOM_USER_ITEM").assertAny(hasText("Name: Mike"))
    }
}