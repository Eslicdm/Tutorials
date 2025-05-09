package com.eslirodrigues.tutorials.utils.swipe

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
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

@Composable
fun TutorialSwipeToDismissScreen() {
    val animalList = remember { mutableStateListOf("Dog", "Cat", "Bird", "Snake") }
    val scope = rememberCoroutineScope()

    LazyColumn {
        items(items = animalList, key = { name -> name }) { animal ->
            val dismissState = rememberSwipeToDismissBoxState(
                initialValue = SwipeToDismissBoxValue.Settled,
                confirmValueChange = {dismissValue ->
//                    if (dismissValue == SwipeToDismissValue.EndToStart) animalList.remove(animal)
                    true
                },
                positionalThreshold = { swipeActivationFloat -> swipeActivationFloat / 3 }
            )
            SwipeToDismissBox(
                modifier = Modifier.animateItem(),
                state = dismissState,
                backgroundContent = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            SwipeToDismissBoxValue.Settled -> Color.LightGray
                            SwipeToDismissBoxValue.StartToEnd -> Color.Green
                            SwipeToDismissBoxValue.EndToStart -> Color.Red
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
                            if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart)
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