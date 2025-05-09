package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedContentSize() {
    var sizeValue by remember { mutableStateOf(200.dp) }
    val animatedSize by animateDpAsState(
        targetValue = sizeValue,
        animationSpec = tween(durationMillis = 2000),
        label = "Change Content Size"
    )
    Box(modifier = Modifier
        .size(animatedSize)
        .background(Blue)
        .clickable { sizeValue = if (sizeValue == 100.dp) 200.dp else 100.dp }
    )


    var isExpanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .background(Red)
        .animateContentSize(tween(2000))
        .size(if (isExpanded) 200.dp else 100.dp)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { isExpanded = !isExpanded }
    )
}