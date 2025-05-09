package com.eslirodrigues.tutorials.utils.snackbar

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BasicSnackbar() {
    var snackbar = "a"
    var showSnackbar by remember { mutableStateOf(false) }
    Scaffold(snackbarHost = {
        if (showSnackbar) {
            Snackbar(
                modifier = Modifier.padding(10.dp),
                actionOnNewLine = true,
                action = {
                    Text(
                        text = "Action",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { showSnackbar = false }
                    )
                },
                dismissAction = {
                    Text(
                        text = "Dismiss",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { showSnackbar = false }
                    )
                }
            ) {
                Text(text = "Are you sure?", fontSize = 16.sp)
            }
        }
    }) {
        Button(onClick = { showSnackbar = true }) {
            Text(text = "Show Snackbar")
        }
    }
}