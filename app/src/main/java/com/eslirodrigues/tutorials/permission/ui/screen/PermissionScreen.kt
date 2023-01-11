package com.eslirodrigues.tutorials.permission.ui.screen

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eslirodrigues.tutorials.R
import com.eslirodrigues.tutorials.permission.ui.components.TutorialPermissionsDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen() {
    val cameraPermissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)
    var buttonCameraResponse by remember { mutableStateOf("Ask for Camera Permission") }
    val showCameraDialog = remember { mutableStateOf(false) }

    val notificationPermissionState = rememberPermissionState(
        permission = android.Manifest.permission.POST_NOTIFICATIONS
    )
    var buttonNotificationResponse by remember { mutableStateOf("Ask for Notification Permission") }
    val showNotificationDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            if (notificationPermissionState.status.isGranted ||
                Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
            ) {
                buttonNotificationResponse = "Notification Permission Granted"
            } else {
                buttonNotificationResponse = "Permission Notification Denied, Ask Again"
                showNotificationDialog.value = true
            }
        }) {
            Text(text = buttonNotificationResponse)
        }
        if (showNotificationDialog.value) TutorialPermissionsDialog(
            title = "Notification Permission",
            text = "Notification Permission is important",
            painterResourceId = R.drawable.ic_notifications,
            showDialog = showNotificationDialog,
            permissionState = notificationPermissionState
        )

        Button(onClick = {
            if (cameraPermissionState.status.isGranted) {
                buttonCameraResponse = "Camera Permission Granted"
            } else {
                buttonCameraResponse = "Permission Camera Denied, Ask Again"
                showCameraDialog.value = true
            }
        }) {
            Text(text = buttonCameraResponse)
        }
        if (showCameraDialog.value) TutorialPermissionsDialog(
            title = "Camera Permission",
            text = "Camera Permission is important",
            painterResourceId = R.drawable.ic_camera,
            showDialog = showCameraDialog,
            permissionState = cameraPermissionState
        )
    }
}