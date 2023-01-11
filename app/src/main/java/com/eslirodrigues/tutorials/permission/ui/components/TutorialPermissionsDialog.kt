package com.eslirodrigues.tutorials.permission.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TutorialPermissionsDialog(
    painterResourceId: Int,
    title: String,
    text: String,
    showDialog: MutableState<Boolean>,
    permissionState: PermissionState
) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
                permissionState.launchPermissionRequest()
            },
            title = { Text(text = title) },
            text = { Text(text = text) },
            icon = {
                Icon(
                    painter = painterResource(id = painterResourceId),
                    contentDescription = null
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog.value = false
                    permissionState.launchPermissionRequest()
                }) {
                    Text(text = "OK")
                }
            }
        )
    }
}