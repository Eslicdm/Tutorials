package com.eslirodrigues.tutorials.databases.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun DatabasesUpdateDialog(
    userName: String,
    userId: String,
    showDialog: MutableState<Boolean>,
    onUpdateClick: (id: String, name: String) -> Unit,
) {
    var inputText by remember { mutableStateOf(userName) }
    AlertDialog(
        onDismissRequest = {
            showDialog.value = false
        },
        text = {
            TextField(
                modifier = Modifier.testTag("UPDATE_TEXT_FIELD"),
                value = inputText,
                onValueChange = { inputText = it }
            )
        },
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