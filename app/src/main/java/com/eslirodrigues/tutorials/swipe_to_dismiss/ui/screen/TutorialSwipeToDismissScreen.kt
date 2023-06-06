package com.eslirodrigues.tutorials.swipe_to_dismiss.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TutorialSwipeToDismissScreen() {
    val animalList = remember { mutableStateListOf("Dog", "Cat", "Bird", "Snake") }
    val scope = rememberCoroutineScope()

    LazyColumn {
        items(items = animalList, key = { name -> name }) { animal ->
            val dismissState = rememberDismissState(
                initialValue = DismissValue.Default,
//                confirmValueChange = { dismissValue ->
//                    if (dismissValue == DismissValue.DismissedToStart) animalList.remove(animal)
//                    true
//                },
                positionalThreshold = { swipeActivationFloat -> swipeActivationFloat / 3 }
            )
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.LightGray
                            DismissValue.DismissedToEnd -> Color.Green
                            DismissValue.DismissedToStart -> Color.Red
                        }
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color),
                        contentAlignment = Alignment.Center
                    ) {
                        Row {
                            IconButton(onClick = { scope.launch { dismissState.reset() } }) {
                                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                            }
                            if (dismissState.targetValue == DismissValue.DismissedToStart)
                                IconButton(onClick = {
                                    animalList.remove(animal)
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                        }
                    }
                },
                dismissContent = {
                    Card {
                        ListItem(
                            headlineContent = { Text(animal) },
                            supportingContent = { Text("Swipe To Delete") }
                        )
                        Divider(thickness = 5.dp, color = Color.Black)
                    }
                }
            )
        }
    }
}