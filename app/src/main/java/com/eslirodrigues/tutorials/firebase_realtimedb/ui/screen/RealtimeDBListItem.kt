package com.eslirodrigues.tutorials.firebase_realtimedb.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eslirodrigues.tutorials.firebase_realtimedb.ui.components.RealtimeDBDialog

@Composable
fun RealtimeDBListItem(
    userName: String,
    userId: String,
    onUpdateClick: (id: String, name: String) -> Unit,
    onDeleteClick: (id: String, name: String) -> Unit
) {
    val showUpdateDialog = remember { mutableStateOf(false) }
    Row(Modifier
        .fillMaxWidth()
        .padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Name: $userName",
            modifier = Modifier.clickable {
                showUpdateDialog.value = true
            }
        )
        IconButton(onClick = {
            onDeleteClick(userId, userName)
        }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
    if (showUpdateDialog.value) {
        RealtimeDBDialog(
            userName = userName,
            userId = userId,
            showDialog = showUpdateDialog,
            onUpdateClick = { id, name ->
                onUpdateClick(id, name)
            }
        )
    }
}