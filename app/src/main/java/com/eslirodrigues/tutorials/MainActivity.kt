package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.camera_video.ui.screen.TutorialCameraVideoScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                TutorialCameraVideoScreen()
            }
        }
    }
}