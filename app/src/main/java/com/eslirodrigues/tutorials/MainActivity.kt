package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.admob.ui.components.AdMobInterstitial
import com.eslirodrigues.tutorials.admob.ui.navigation.AdMobNavGraph
import com.eslirodrigues.tutorials.admob.ui.navigation.AdMobNavRoute
import com.eslirodrigues.tutorials.admob.ui.screen.AdScreen
import com.eslirodrigues.tutorials.animated_navigation.ui.navigation.NavAnimatedGraph
import com.eslirodrigues.tutorials.di_hilt.ui.screen.DependencyScreen
import com.eslirodrigues.tutorials.lazy_row_column_grid.ui.screen.TutorialLazyScreen
import com.eslirodrigues.tutorials.navigation.ui.navigation.NavGraph
import com.eslirodrigues.tutorials.navigation_drawer.ui.navigation.NavDrawerGraph
import com.eslirodrigues.tutorials.stickyheader_lazycolumn.ui.screen.StickyHeaderScreen
import com.eslirodrigues.tutorials.swipetorefresh_accompanist.ui.screen.SwipeAccompanistScreen
import com.eslirodrigues.tutorials.toast.ui.screen.ToastScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                ToastScreen()
            }
        }
    }
}