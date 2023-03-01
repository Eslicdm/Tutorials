package com.eslirodrigues.tutorials.firebase_messaging.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eslirodrigues.tutorials.firebase_messaging.ui.components.FirebaseMessagingNotificationButton
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

@Composable
fun FirebaseMessagingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FirebaseMessagingNotificationButton(
            onClick = { Firebase.messaging.subscribeToTopic("Tutorial") },
            buttonText = "Subscribe to Topic"
        )
    }
}
