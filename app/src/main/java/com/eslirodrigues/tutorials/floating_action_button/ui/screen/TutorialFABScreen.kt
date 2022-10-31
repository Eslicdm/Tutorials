package com.eslirodrigues.tutorials.floating_action_button.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialFABScreen() {
    val lazyListState = rememberLazyListState()
    val list = (0..100).toList()

    val isExpanded by remember { derivedStateOf { lazyListState.firstVisibleItemIndex == 0 } }
    val showFAB by remember { derivedStateOf { lazyListState.firstVisibleItemIndex <= 20 } }

    Scaffold(
        floatingActionButton = {
//            if (showFAB) {
//                FloatingActionButton(onClick = { /*TODO*/ }) {
//                    Icon(Icons.Default.Add, contentDescription = "Add")
//                }
//            }

//            Column {
//                SmallFloatingActionButton(
//                    modifier = Modifier.align(Alignment.End),
//                    onClick = { /*TODO*/ }
//                ) {
//                    Icon(Icons.Default.Edit, contentDescription = "Edit")
//                }
//                // Only for scrollable screens
//                ExtendedFloatingActionButton(
//                    text = { Text(text = "Add") },
//                    icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
//                    expanded = isExpanded,
//                    onClick = { /*TODO*/ }
//                )
//            }
            LargeFloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Surface {
            LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
                items(list) { index ->
                    Text(text = "Item - $index")
                    if (index == 100) Spacer(modifier = Modifier.padding(bottom = 120.dp))
                }
            }
        }
    }
}