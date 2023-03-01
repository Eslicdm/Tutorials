package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.bottom_app_bar.ui.screen.TutorialBottomAppBarScreen
import com.eslirodrigues.tutorials.chips.ui.screen.ChipsScreen
import com.eslirodrigues.tutorials.data_store.ui.screen.DataStoreScreen
import com.eslirodrigues.tutorials.firebase_firestore.ui.screen.FirestoreDBScreen
import com.eslirodrigues.tutorials.firebase_messaging.ui.screen.FirebaseMessagingScreen
import com.eslirodrigues.tutorials.menu.ui.screen.MenuScreen
import com.eslirodrigues.tutorials.navigation_drawer.ui.navigation.NavDrawerGraph
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.screen.UserScreen
import com.eslirodrigues.tutorials.swipe.ui.screen.TutorialSwipeScreen
import com.eslirodrigues.tutorials.switch.ui.screen.TutorialSwitchScreen
import com.eslirodrigues.tutorials.tab.ui.screen.TutorialTabScreen
import com.eslirodrigues.tutorials.topappbar.ui.screen.TopAppBarScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                TutorialSwipeScreen()
            }
        }
    }
}