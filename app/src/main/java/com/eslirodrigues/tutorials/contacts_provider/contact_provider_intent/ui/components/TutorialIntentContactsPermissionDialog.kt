package com.eslirodrigues.tutorials.contacts_provider.contact_provider_intent.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TutorialIntentContactsPermissionDialog(
    painterResourceId: Int,
    title: String,
    text: String,
    permissionState: PermissionState
) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
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
                    showDialog = false
                    permissionState.launchPermissionRequest()
                }) {
                    Text(text = "OK")
                }
            }
        )
    }
}