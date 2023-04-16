package com.eslirodrigues.tutorials.snackbar.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun SnackBarScreen() {
//    BasicSnackbar()
//    StateHostSnackBar()
    CustomSnackbar()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicSnackbar() {
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
            Text(text = "Show SnackBar")
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateHostSnackBar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) {
        Button(onClick = {
            scope.launch {
                val actionLabelString: String? = "Action"
                val isActionLabelEmpty = actionLabelString.isNullOrEmpty()
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
                        Toast.makeText(context, "Action", Toast.LENGTH_SHORT).show()
                    }
                    SnackbarResult.Dismissed -> {
                        Toast.makeText(context, "dismissed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }) {
            Text(text = "Show SnackBar")
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSnackbar() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState) { snackBarData ->
            Row(Modifier
                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(10.dp))
                .padding(10.dp)
            ) {
                Text(text = snackBarData.visuals.message)
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                if (!snackBarData.visuals.actionLabel.isNullOrEmpty()) {
                    Text(
                        text = snackBarData.visuals.actionLabel!!,
                        modifier = Modifier.clickable {
                            Toast.makeText(context, "Action", Toast.LENGTH_SHORT).show()
                            snackBarData.performAction()
                        }
                    )
                }
                Text(
                    text = "Dismiss",
                    modifier = Modifier.clickable {
                        Toast.makeText(context, "Dismissed", Toast.LENGTH_SHORT).show()
                        snackBarData.dismiss()
                    }
                )
                IconButton(onClick = {
                    Toast.makeText(context, "Dismiss", Toast.LENGTH_SHORT).show()
                    snackBarData.dismiss()

                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
            Text(text = "Show SnackBar")
        }
    }
}