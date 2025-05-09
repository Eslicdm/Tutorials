package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun AnimatedPosition() {
    var isMoved by remember { mutableStateOf(false) }
    val xOffset = with(LocalDensity.current) { -90.dp.toPx().roundToInt() }
    val yOffset = with(LocalDensity.current) { 20.dp.toPx().roundToInt() }
    val offset by animateIntOffsetAsState(
        targetValue = if (isMoved) IntOffset(xOffset, yOffset) else IntOffset.Zero,
        label = "change offset"
    )
    Box(modifier = Modifier
        .offset { offset }
        .size(100.dp)
        .background(Red)
        .clickable { isMoved = !isMoved }
    )


    var isBoxMoved by remember { mutableStateOf(false) }
    val offsetTarget = if (isBoxMoved) IntOffset(300, 0) else IntOffset.Zero
    val boxOffset by animateIntOffsetAsState(
        targetValue = offsetTarget,
        animationSpec = tween(50),
        label = "offset"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { isBoxMoved = !isBoxMoved }
    ) {
        Box(modifier = Modifier.size(100.dp).background(Red))
        Box(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val offsetValue = if (isLookingAhead) offsetTarget else boxOffset
                    val placeable = measurable.measure(constraints)
                    val height = if (isBoxMoved) offsetValue.y else placeable.height + offsetValue.y
                    layout(
                        width = placeable.width + offsetValue.x,
                        height = height
                    ) { placeable.placeRelative(offsetValue) }
                }
                .size(100.dp)
                .background(Green)
        )
        Box(modifier = Modifier.size(100.dp).background(Blue))
    }
}