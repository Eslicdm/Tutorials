package com.eslirodrigues.tutorials.lazy_row_column_grid.ui.screen

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.eslirodrigues.tutorials.MainActivity
import com.eslirodrigues.tutorials.utils.lazy_row_column_grid.TestTagsConstants.INDEX_CAT_NUMBER
import com.eslirodrigues.tutorials.utils.lazy_row_column_grid.TestTagsConstants.LAZY_COLUMN
import com.eslirodrigues.tutorials.utils.lazy_row_column_grid.TutorialLazyScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TutorialLazyScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeRule.activity.setContent { TutorialLazyScreen() }
    }

    @Test
    fun assert_IsLazyColumnDisplayed() {
        composeRule.onNodeWithTag(LAZY_COLUMN).assertIsDisplayed()
    }

    @Test
    fun scrollLazyColumn_Assert_ScrollToTopButtonIsDisplayed() {
        composeRule.onNodeWithTag(LAZY_COLUMN).performScrollToIndex(1)
        composeRule.onNodeWithContentDescription("scroll to top").assertIsDisplayed()
    }

    @Test
    fun scrollLazyColumn_Assert_ScrollToTopButtonIsClickable() {
        composeRule.onNodeWithTag(LAZY_COLUMN).performScrollToIndex(1)
        composeRule.onNodeWithContentDescription("scroll to top").assertHasClickAction()
    }

    @Test
    fun scrollToIndex15_And_ClickScrollToTopButton_Assert_Index0IsDisplayed() {
        composeRule.onNodeWithTag(LAZY_COLUMN).performScrollToIndex(15)
        composeRule.onNodeWithContentDescription("scroll to top").performTouchInput { click() }
        composeRule.onAllNodesWithTag(INDEX_CAT_NUMBER)[0].assertTextContains("Number: 0")
    }
}