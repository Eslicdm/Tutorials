package com.eslirodrigues.tutorials.utils.snackbar

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.eslirodrigues.tutorials.utils.toast
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomSnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState) { snackbarData ->
            Row(
                Modifier
                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(10.dp))
                .padding(10.dp)
            ) {
                Text(text = snackbarData.visuals.message)
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                if (!snackbarData.visuals.actionLabel.isNullOrEmpty()) {
                    Text(
                        text = snackbarData.visuals.actionLabel!!,
                        modifier = Modifier.clickable {
                            Toast.makeText(context, "Action", Toast.LENGTH_SHORT).show()
                            snackbarData.performAction()
                        }
                    )
                }
                Text(
                    text = "Dismiss",
                    modifier = Modifier.clickable {
                        context.toast("Dismissed")
                        snackbarData.dismiss()
                    }
                )
                IconButton(onClick = {
                    context.toast("Dismiss")
                    snackbarData.dismiss()

                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }
    }) {
        Button(onClick = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Are you sure?",
                    actionLabel = "Action"
                )
            }
        }) {
            Text(text = "Show Snackbar")
        }
    }
}