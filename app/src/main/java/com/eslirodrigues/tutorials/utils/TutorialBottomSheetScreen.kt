package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TutorialBottomSheetScreen() {
    var showBottomSheetScaffold by rememberSaveable { mutableStateOf(false) }
    val showModalBottomSheet = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            showBottomSheetScaffold = !showBottomSheetScaffold
        }) {
            Text(text = "Show/Hide BottomSheetScaffold")
        }
        Button(onClick = {
            showModalBottomSheet.value = !showModalBottomSheet.value
        }) {
            Text(text = "Show/Hide ModalBottomSheet")
        }
    }

    if (showBottomSheetScaffold) TutorialBottomSheetScaffold()
    TutorialModalBottomSheet(showModalBottomSheet)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialBottomSheetScaffold() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 140.dp,
        sheetContent = {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start)
            ) {
                Icon(Icons.Default.Share, contentDescription = "Share")
                Text(text = "Share")
            }

            Button(onClick = { scope.launch { scaffoldState.bottomSheetState.expand() } }) {
                Text(text = "Expand BottomSheet")
            }
            Button(onClick = { scope.launch { scaffoldState.bottomSheetState.partialExpand() } }) {
                Text(text = "PartialExpand BottomSheet")
            }
        },
        content = { Text(text = "Scaffold Content") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialModalBottomSheet(showModalBottomSheet: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()
    var skipPartially by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartially)

    if (showModalBottomSheet.value)
        ModalBottomSheet(
            onDismissRequest = { showModalBottomSheet.value = false },
            sheetState = bottomSheetState,
        ) {
            Column(Modifier.fillMaxSize()) {
                Button(
                    onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                showModalBottomSheet.value = false
                            }
                        }
                    }
                ) {
                    Text("Hide Bottom Sheet")
                }
                Button(onClick = {
                    scope.launch {
                        showModalBottomSheet.value = false
                        skipPartially = !skipPartially
                        delay(500L)
                        showModalBottomSheet.value = true
                    }
                }) {
                    Text(text = "Skip Partially Expanded")
                }
            }
        }
}