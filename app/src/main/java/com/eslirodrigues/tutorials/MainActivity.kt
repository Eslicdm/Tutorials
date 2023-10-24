package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.animations.ui.screen.TutorialAnimationsScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import com.eslirodrigues.tutorials.window_manager.ui.navigation.TutorialWindowManagerNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                TutorialAnimationsScreen()
            }
        }
    }
}