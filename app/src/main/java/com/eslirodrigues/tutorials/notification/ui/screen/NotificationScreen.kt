package com.eslirodrigues.tutorials.notification.ui.screen

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.tutorials.notification.ui.components.TutorialNotificationButton
import com.eslirodrigues.tutorials.notification.ui.viewmodel.NotificationViewModel

@Composable
fun NotificationScreen(viewModel: NotificationViewModel = hiltViewModel()) {
    var textAndTitleText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Button(onClick = { viewModel.openNotificationSettings() }) {
                Text(text = "Open Channel Settings")
            }
        }

        Row {
            TutorialNotificationButton(
                onClick = {
                    viewModel.showNotificationWithTextAndTitle(
                        title = "Text and Title",
                        content = "$textAndTitleText, Text and Title Content"
                    )
                },
                buttonText = "Text and Title"
            )
            TextField(value = textAndTitleText, onValueChange = { textAndTitleText = it })
        }

        TutorialNotificationButton(
            onClick = {
                viewModel.showNotificationWithTapAction(
                    title = "Tap Action",
                    content = "Tap Action Content"
                )
            },
            buttonText = "Tap Action"
        )

        TutorialNotificationButton(
            onClick = {
                viewModel.showNotificationWithResponseAction(
                    title = "Response Action",
                    content = "Response Action Content"
                )
            },
            buttonText = "Response Action"
        )

        TutorialNotificationButton(
            onClick = {
                viewModel.showNotificationWithProgressBar()
            },
            buttonText = "Start Progress Bar"
        )

        TutorialNotificationButton(
            onClick = {
                viewModel.showNotificationWithGroup()
            },
            buttonText = "Group"
        )

        TutorialNotificationButton(
            onClick = {
                viewModel.cancelNotifications(
                    notificationIDs = null, shouldCancelAll = true
                )
            },
            buttonText = "Cancel Notifications"
        )
    }
}