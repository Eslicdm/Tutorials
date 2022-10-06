package com.eslirodrigues.tutorials.firebase_crashlytics.ui.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TutorialCrashlyticsScreen() {
    Button(onClick = { throw RuntimeException("Test Crash")  }) {
        Text(text = "BUG")
    }
}