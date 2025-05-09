package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun TutorialDialogScreen() {
    var showAlertDialog by remember { mutableStateOf(false) }
    var numberAlertDialog by remember { mutableIntStateOf(0) }

    var showDialog by remember { mutableStateOf(false) }
    var numberDialog by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = numberAlertDialog.toString(), fontSize = 32.sp)
        Button(onClick = { showAlertDialog = true }) {
            Text(text = "Change number AlertDialog")
        }

        Text(text = numberDialog.toString(), fontSize = 32.sp)
        Button(onClick = { showDialog = true }) {
            Text(text = "Change number Dialog")
        }
    }

    if (showAlertDialog) {
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            icon = { Icon(Icons.Default.Money, contentDescription = "Number") },
            title = { Text(text = "Want to change the number?") },
            text = { Text(text = "The number will change to a random value from 1 to 100") },
            dismissButton = {
                TextButton(onClick = {
                    showAlertDialog = false
                }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    numberAlertDialog = (1..100).random()
                    showAlertDialog = false
                }) {
                    Text(text = "Confirm")
                }
            }
        )
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Card {
                Icon(Icons.Default.Money, contentDescription = "Number")
                Text(text = "Want to change the number?")
                Text(text = "The number will change to a random value from 1 to 100")
                Row {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {
                        numberDialog = (1..100).random()
                        showDialog = false
                    }) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
}