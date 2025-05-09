package com.eslirodrigues.tutorials.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateZoomBy
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TutorialZoomScreen() {
    val coroutineScope = rememberCoroutineScope()
    var scale by remember { mutableFloatStateOf(1f) }
    val state = rememberTransformableState { zoomChange, _, _ ->
        scale *= zoomChange
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .graphicsLayer(scaleX = scale, scaleY = scale)
                .transformable(state)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { coroutineScope.launch { state.animateZoomBy(2f) } },
                        onTap = { coroutineScope.launch { state.animateZoomBy(0.5f) } }
                    )
                }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        change.consume()
                        if (dragAmount in 0f..10f) coroutineScope.launch { state.animateZoomBy(1.3f) }
                        if (dragAmount in -10f..-1f) coroutineScope.launch { state.animateZoomBy(0.8f) }
                    }
                }
                .size(100.dp)
                .background(Color.Red)
        )
    }
}