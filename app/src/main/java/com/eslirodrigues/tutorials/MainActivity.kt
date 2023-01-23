package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.alarm_manager.ui.screen.AlarmManagerScreen
import com.eslirodrigues.tutorials.coil.ui.screen.TutorialCoilScreen
import com.eslirodrigues.tutorials.countdowntimer.ui.screen.TutorialCountDownTimerScreen
import com.eslirodrigues.tutorials.di_koin.ui.screen.KoinScreen
import com.eslirodrigues.tutorials.notification.ui.navigation.NotificationNavGraph
import com.eslirodrigues.tutorials.notification.ui.screen.NotificationScreen
import com.eslirodrigues.tutorials.permission.ui.screen.PermissionScreen
import com.eslirodrigues.tutorials.sqldelight_database.ui.screen.SqlDelightUserScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                SqlDelightUserScreen()
            }
        }
    }
}