package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.floating_action_button.ui.screen.TutorialFABScreen
import com.eslirodrigues.tutorials.navigation_bar.ui.navigation.TutorialNavigationBarNavGraph
import com.eslirodrigues.tutorials.radio_button_checkbox.ui.screen.RadioButtonAndCheckboxScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                RadioButtonAndCheckboxScreen()
            }
        }
    }
}