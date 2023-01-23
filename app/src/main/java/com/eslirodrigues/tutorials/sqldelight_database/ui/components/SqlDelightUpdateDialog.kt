package com.eslirodrigues.tutorials.sqldelight_database.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*

@Composable
fun SqlDelightUpdateDialog(
    userName: String,
    userId: String,
    showDialog: MutableState<Boolean>,
    onUpdateClick: (id: String, name: String) -> Unit,
) {
    var inputText by remember { mutableStateOf(userName) }
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        text = { TextField(value = inputText, onValueChange = { inputText = it }) },
        confirmButton = {
            Button(onClick = {
                onUpdateClick(userId, inputText)
                showDialog.value = false
            }) {
                Text(text = "Update")
            }
        }
    )
}