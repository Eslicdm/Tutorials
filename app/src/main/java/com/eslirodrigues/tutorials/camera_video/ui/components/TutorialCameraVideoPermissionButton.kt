package com.eslirodrigues.tutorials.camera_video.ui.components

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
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TutorialCameraVideoPermissionButton(
    onClick: () -> Unit,
    text: String
) {
    var showPermissionDialog by remember { mutableStateOf(false) }
    val multiPermissionsState = rememberMultiplePermissionsState(listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    ))

    if (showPermissionDialog) TutorialCameraVideoPermissionDialog(multiPermissionsState)

    Button(onClick = {
        if (multiPermissionsState.allPermissionsGranted) onClick() else showPermissionDialog = true
    }) { Text(text = text) }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TutorialCameraVideoPermissionDialog(permissionsState: MultiplePermissionsState) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                permissionsState.launchMultiplePermissionRequest()
            },
            title = { Text(text = "Camera and Audio Permissions") },
            text = { Text(text = "Camera and Audio permissions are required to use this app") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "Camera"
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    permissionsState.launchMultiplePermissionRequest()
                }) {
                    Text(text = "OK")
                }
            }
        )
    }
}