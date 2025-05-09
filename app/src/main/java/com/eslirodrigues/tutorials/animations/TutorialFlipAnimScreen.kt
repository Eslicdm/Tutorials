package com.eslirodrigues.tutorials.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateRotateBy
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TutorialFlipAnimScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        ReverseFlip()
        BlockedFlip()
    }
}

@Composable
private fun ReverseFlip() {
    val coroutineScope = rememberCoroutineScope()
    var rotationY by remember { mutableFloatStateOf(0f) }
    val stateY = rememberTransformableState { _, _, rotationChange ->
        rotationY += rotationChange
    }

    Box(
        modifier = Modifier
            .graphicsLayer(rotationY = rotationY)
            .transformable(stateY)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { coroutineScope.launch { stateY.animateRotateBy(180f) } },
                    onDoubleTap = { coroutineScope.launch { stateY.animateRotateBy(360f) } },
                )
            }
            .size(100.dp)
            .background(Color.Red),
        content = { Text(text = "REVERSE", fontWeight = FontWeight.ExtraBold) }
    )
}

@Composable
private fun BlockedFlip() {
    val coroutineScope = rememberCoroutineScope()
    var rotationY by remember { mutableFloatStateOf(0f) }
    val stateY = rememberTransformableState { _, _, rotationChange ->
        rotationY += rotationChange
    }

    Box(
        modifier = Modifier
            .graphicsLayer(rotationY = rotationY)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    when {
                        rotationY < -75 -> coroutineScope.launch { stateY.animateRotateBy(75f) }
                        rotationY > 75 -> coroutineScope.launch { stateY.animateRotateBy(-75f) }
                        else -> rotationY += dragAmount.x
                    }
                }
            }
            .size(100.dp)
            .background(Color.Red),
        content = { Text(text = "BLOCKED", fontWeight = FontWeight.ExtraBold) }
    )
}