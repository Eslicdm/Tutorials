package com.eslirodrigues.tutorials.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.animateRotateBy
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.input.pointer.isOutOfBounds
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TutorialRotationScreen() {
    val coroutineScope = rememberCoroutineScope()
    var rotationZ by remember { mutableFloatStateOf(0f) }
    val stateZ = rememberTransformableState { _, _, rotationChange ->
        rotationZ += rotationChange
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .graphicsLayer(rotationZ = rotationZ)
                .transformable(stateZ)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            coroutineScope.launch { stateZ.animateRotateBy(45f) }
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        val isOutOfBounds =
                            change.isOutOfBounds(
                                IntSize(size.width, size.height),
                                extendedTouchPadding
                            )
                        if (!isOutOfBounds) rotationZ += dragAmount.x
                    }
                }
                .size(100.dp)
                .background(Color.Red)
                .border(4.dp, Color.Black, shape = CutCornerShape(topStart = 40.dp))
        )
    }
}