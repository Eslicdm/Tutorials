package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp

@Composable
fun RepeatableAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val animatedColorInfinite by infiniteTransition.animateColor(
        initialValue = Red,
        targetValue = Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "infinite color"
    )
    Box(modifier = Modifier.size(200.dp).drawBehind { drawRect(animatedColorInfinite) })

    var startAnimation by remember { mutableStateOf(false) }
    val animatedColorFinite by animateColorAsState(
        targetValue = if (startAnimation) Green else Red,
        animationSpec = repeatable (
            iterations = 2,
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(1000)
        ),
        label = "finite color"
    )
    Box(modifier = Modifier
        .size(200.dp)
        .drawBehind { drawRect(animatedColorFinite) }
        .clickable { startAnimation = !startAnimation }
    )
}