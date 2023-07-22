package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.brush_gradient.ui.screen.TutorialBrushGradient
import com.eslirodrigues.tutorials.canvas.ui.screen.TutorialCanvas
import com.eslirodrigues.tutorials.horizontal_pager_on_boarding.ui.screen.TutorialHorizontalPagerOnBoardingScreen
import com.eslirodrigues.tutorials.paging.ui.screen.TutorialPagingScreen
import com.eslirodrigues.tutorials.savedstatehandle.ui.screen.TutorialSavedStateHandleScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import com.eslirodrigues.tutorials.use_case.ui.screen.TutorialUseCaseScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                TutorialHorizontalPagerOnBoardingScreen()
            }
        }
    }
}