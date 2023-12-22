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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissState
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
            val dismissState = rememberSwipeToDismissState(
                initialValue = SwipeToDismissValue.Settled,
                confirmValueChange = {dismissValue ->
//                    if (dismissValue == SwipeToDismissValue.EndToStart) animalList.remove(animal)
                    true
                },
                positionalThreshold = { swipeActivationFloat -> swipeActivationFloat / 3 }
            )
            SwipeToDismissBox(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                backgroundContent = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            SwipeToDismissValue.Settled -> Color.LightGray
                            SwipeToDismissValue.StartToEnd -> Color.Green
                            SwipeToDismissValue.EndToStart -> Color.Red
                        }, label = "change color"
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
                            if (dismissState.targetValue == SwipeToDismissValue.EndToStart)
                                IconButton(onClick = { animalList.remove(animal) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                        }
                    }
                },
                enableDismissFromStartToEnd = true,
                enableDismissFromEndToStart = true,
                content = {
                    Card {
                        ListItem(
                            headlineContent = { Text(animal) },
                            supportingContent = { Text("Swipe To Delete") }
                        )
                        HorizontalDivider(thickness = 5.dp, color = Color.Black)
                    }
                }
            )
        }
    }
}