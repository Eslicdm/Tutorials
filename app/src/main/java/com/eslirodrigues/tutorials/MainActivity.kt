package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.eslirodrigues.tutorials.admob.ui.components.AdMobInterstitial
import com.eslirodrigues.tutorials.admob.ui.navigation.AdMobNavGraph
import com.eslirodrigues.tutorials.admob.ui.navigation.AdMobNavRoute
import com.eslirodrigues.tutorials.admob.ui.screen.AdScreen
import com.eslirodrigues.tutorials.animated_navigation.ui.navigation.NavAnimatedGraph
import com.eslirodrigues.tutorials.chips.ui.screen.ChipsScreen
import com.eslirodrigues.tutorials.di_hilt.ui.screen.DependencyScreen
import com.eslirodrigues.tutorials.firebase_auth.ui.screen.TutorialFirebaseAuthScreen
import com.eslirodrigues.tutorials.firebase_crashlytics.ui.screen.TutorialCrashlyticsScreen
import com.eslirodrigues.tutorials.firebase_realtimedb.ui.screen.TutorialRealtimeDBScreen
import com.eslirodrigues.tutorials.lazy_row_column_grid.ui.screen.TutorialLazyScreen
import com.eslirodrigues.tutorials.menu.ui.screen.MenuScreen
import com.eslirodrigues.tutorials.navigation.ui.navigation.NavGraph
import com.eslirodrigues.tutorials.navigation_drawer.ui.navigation.NavDrawerGraph
import com.eslirodrigues.tutorials.snackbar.ui.screen.SnackBarScreen
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
                TutorialCrashlyticsScreen()
            }
        }
    }
}