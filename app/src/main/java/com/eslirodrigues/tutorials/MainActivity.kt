package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.navigation.ui.navigation.NavGraph
import com.eslirodrigues.tutorials.navigation_drawer.ui.navigation.NavDrawerGraph
import com.eslirodrigues.tutorials.stickyheader_lazycolumn.ui.screen.StickyHeaderScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                NavDrawerGraph()
            }
        }
    }
}