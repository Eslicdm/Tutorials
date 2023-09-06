package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.ktor_client.ui.screen.KtorClientScreen
import com.eslirodrigues.tutorials.marquee.ui.screen.TutorialMarqueeScreen
import com.eslirodrigues.tutorials.navigation.ui.navigation.NavGraph
import com.eslirodrigues.tutorials.pull_refresh.ui.screen.PullRefreshScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import com.eslirodrigues.tutorials.web_view.ui.screen.WebViewScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                TutorialMarqueeScreen()
            }
        }
    }
}