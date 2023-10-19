package com.eslirodrigues.tutorials.alert_dialog.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun TutorialDialogScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var number by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = number.toString(), fontSize = 32.sp)
        Button(onClick = { showDialog = true }) {
            Text(text = "Change number")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            icon = { Icon(Icons.Default.Money, contentDescription = "Number") },
            title = { Text(text = "Want to change the number?") },
            text = { Text(text = "The number will change to a random value from 1 to 100") },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    number = (1..100).random()
                    showDialog = false
                }) {
                    Text(text = "Confirm")
                }
            }
        )


//        Dialog(onDismissRequest = { showDialog = false }) {
//            Card {
//                Icon(Icons.Default.Money, contentDescription = "Number")
//                Text(text = "Want to change the number?")
//                Text(text = "The number will change to a random value from 1 to 100")
//                Row {
//                    TextButton(onClick = {
//                        showDialog = false
//                    }) {
//                        Text(text = "Cancel")
//                    }
//                    TextButton(onClick = {
//                        number = (1..100).random()
//                        showDialog = false
//                    }) {
//                        Text(text = "Confirm")
//                    }
//                }
//            }
//        }
    }
}