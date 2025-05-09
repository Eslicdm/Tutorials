package com.eslirodrigues.tutorials.utils.snackbar

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.eslirodrigues.tutorials.utils.toast
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StateHostSnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) {
        Button(onClick = {
            scope.launch {
                val actionLabelString = "Action"
                val isActionLabelEmpty = actionLabelString.isEmpty()
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = "Are you sure?",
                    actionLabel = actionLabelString,
                    withDismissAction = !isActionLabelEmpty,
                    duration = if (isActionLabelEmpty)  {
                        SnackbarDuration.Short
                    } else SnackbarDuration.Indefinite
                )

                when(snackbarResult) {
                    SnackbarResult.ActionPerformed -> {
                        context.toast("Action")
                    }
                    SnackbarResult.Dismissed -> {
                        context.toast("Dismissed")
                    }
                }
            }
        }) {
            Text(text = "Show Snackbar")
        }
    }
}