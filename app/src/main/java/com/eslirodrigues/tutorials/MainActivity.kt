package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.topappbar_compose.ui.screen.TopAppBarScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                TopAppBarScreen()
            }
        }
    }
}