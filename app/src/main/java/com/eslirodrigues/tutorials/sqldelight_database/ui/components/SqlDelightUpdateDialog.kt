package com.eslirodrigues.tutorials.sqldelight_database.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
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