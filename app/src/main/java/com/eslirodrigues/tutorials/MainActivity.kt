package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.eslirodrigues.tutorials.alert_dialog.ui.screen.TutorialDialogScreen
import com.eslirodrigues.tutorials.firebase_auth.ui.navigation.FirebaseAuthNavGraph
import com.eslirodrigues.tutorials.firebase_auth.ui.screen.auth.FirebaseAuthSignInScreen
import com.eslirodrigues.tutorials.firebase_auth.ui.screen.auth.FirebaseAuthSignUpScreen
import com.eslirodrigues.tutorials.firebase_realtimedb.ui.screen.RealtimeDBScreen
import com.eslirodrigues.tutorials.room_database.ui.screen.RoomUserScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                TutorialDialogScreen()
            }
        }
    }
}