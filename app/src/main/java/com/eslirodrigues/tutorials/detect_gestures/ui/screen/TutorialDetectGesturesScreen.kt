package com.eslirodrigues.tutorials.detect_gestures.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// the events propagate: ListItem->Button. Top-to-Bottom Tree
// avoid custom gestures, use basic modifiers like clickable (Accessibility)
@Composable
fun TutorialDetectGesturesScreen() {
    var horizontalText by remember { mutableStateOf("") }
    var horizontalCount by remember { mutableIntStateOf(0) }

    var verticalText by remember { mutableStateOf("") }
    var verticalCount by remember { mutableIntStateOf(0) }

    var freeText by remember { mutableStateOf("") }
    var freeCount by remember { mutableIntStateOf(0) }

    var tapText by remember { mutableStateOf("") }
    var tapCount by remember { mutableIntStateOf(0) }

    var dragAfterLongPressText by remember { mutableStateOf("") }
    var dragAfterLongPressCount by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = horizontalText, fontSize = 22.sp)
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(onDragStart = { horizontalCount = 0 }) { _, _ ->
                    horizontalCount += 1
                    horizontalText =
                        "Horizontal ${if (horizontalCount > 15) "Valid" else "$horizontalCount"}"
                }
            }
        )

        Text(text = verticalText, fontSize = 22.sp)
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragStart = { verticalText = "Vertical Start " },
                    onDragCancel = { verticalText += "Vertical Cancel" },
                    onDragEnd = { verticalText += " End " },
                    onVerticalDrag = { change, dragAmount ->
                        if (change.position.x in 100.0..150.0 && change.position.y in 10.0..250.0) {
                            verticalCount += 1
                            verticalText = "Consumed $verticalCount - $dragAmount"
                        }
                    }
                )
            }
        )

        Text(text = freeText, fontSize = 22.sp)
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                awaitEachGesture {
                    while (true) {
                        val event = awaitPointerEvent()
                        if (event.changes.any { it.isConsumed }) {
                            return@awaitEachGesture
                        } else {
                            event.changes.forEach { it.consume() }
                            freeCount += 1
                            freeText = "Free $freeCount"
                        }
                    }
                }
            }
        )

        Text(text = tapText, fontSize = 22.sp)
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                detectTapGestures {
                    tapCount += 1
                    tapText = "Tap $tapCount"
                }
            }
        )

        Text(text = dragAfterLongPressText, fontSize = 22.sp)
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress { _, _ ->
                    dragAfterLongPressCount += 1
                    dragAfterLongPressText = "Long $dragAfterLongPressCount"
                }
            }
        )
    }
}