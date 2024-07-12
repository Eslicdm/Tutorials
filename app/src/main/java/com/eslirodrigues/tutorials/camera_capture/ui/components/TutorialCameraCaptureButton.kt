package com.eslirodrigues.tutorials.camera_capture.ui.components

import android.Manifest
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.eslirodrigues.tutorials.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TutorialCameraCaptureButton(
    onClick: () -> Unit,
    text: String
) {
    var showPermissionDialog by remember { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    if (showPermissionDialog) TutorialCameraCapturePermissionDialog(cameraPermissionState)

    Button(onClick = {
        if (cameraPermissionState.status.isGranted) onClick() else showPermissionDialog = true
    }) { Text(text = text) }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TutorialCameraCapturePermissionDialog(cameraPermissionState: PermissionState) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                cameraPermissionState.launchPermissionRequest()
            },
            title = { Text(text = "Camera Permission") },
            text = { Text(text = "Camera permission is required to use this app") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "Camera"
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    cameraPermissionState.launchPermissionRequest()
                }) {
                    Text(text = "OK")
                }
            }
        )
    }
}