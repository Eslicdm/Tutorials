package com.eslirodrigues.tutorials.swipe_to_reveal.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private enum class HorizontalDragValue { Settled, StartToEnd, EndToStart }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialSwipeToRevealScreen() {
    val animalList = remember { mutableStateListOf("Dog", "Cat", "Bird", "Snake") }

    LazyColumn {
        items(items = animalList, key = { name -> name }) { animal ->
            var boxSize by remember { mutableFloatStateOf(0F) }
            val scope = rememberCoroutineScope()
            val anchors = DraggableAnchors {
                HorizontalDragValue.Settled at 0f
                HorizontalDragValue.StartToEnd at boxSize / 3
                HorizontalDragValue.EndToStart at -boxSize / 3
            }
            val state = remember {
                AnchoredDraggableState(
                    initialValue = HorizontalDragValue.Settled,
                    positionalThreshold = { distance -> distance * 0.3f },
                    velocityThreshold = { 0.3f },
                    animationSpec = tween(),
                )
            }
            SideEffect { state.updateAnchors(anchors) }

            val iconsBackgroundColor by animateColorAsState(
                when (state.targetValue) {
                    HorizontalDragValue.Settled -> Color.DarkGray
                    HorizontalDragValue.StartToEnd -> Color.Green
                    HorizontalDragValue.EndToStart -> Color.Red
                }, label = "change color"
            )
            Box(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
                Box(modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(70.dp)
                    .background(iconsBackgroundColor)
                    .border(2.dp, Color.White)
                    .align(Alignment.CenterStart),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { scope.launch { state.animateTo(HorizontalDragValue.Settled) } }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorite", tint = Color.White)
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(70.dp)
                    .background(iconsBackgroundColor)
                    .border(2.dp, Color.White)
                    .align(Alignment.CenterEnd),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { animalList.remove(animal) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                    }
                }
                Box(modifier = Modifier
                    .graphicsLayer { boxSize = size.width }
                    .offset { IntOffset(x = state.requireOffset().roundToInt(), y = 0) }
                    .fillMaxWidth()
                    .height(70.dp)
                    .anchoredDraggable(state, Orientation.Horizontal)
                    .background(Color.LightGray)
                    .border(2.dp, Color.White)
                ) {
                    Text(text = animal, fontSize = 26.sp, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}