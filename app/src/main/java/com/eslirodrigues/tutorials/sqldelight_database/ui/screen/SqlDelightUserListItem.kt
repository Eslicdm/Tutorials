package com.eslirodrigues.tutorials.sqldelight_database.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eslirodrigues.tutorials.sqldelight_database.ui.components.SqlDelightUpdateDialog

@Composable
fun SqlDelightUserListItem(
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
        SqlDelightUpdateDialog(
            userName = userName,
            userId = userId,
            showDialog = showUpdateDialog,
            onUpdateClick = { id, name ->
                onUpdateClick(id, name)
            }
        )
    }
}