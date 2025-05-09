package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SequentialAndConcurrentAnimations() {
    val sequentialColorAnimation = remember { Animatable(Green) }
    val sequentialAlphaAnimation = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        sequentialColorAnimation.animateTo(Red, tween(2000))
        sequentialAlphaAnimation.animateTo(0.1f, tween(2000))
    }
    Box(modifier = Modifier
        .size(200.dp)
        .drawBehind {
            drawRect(sequentialColorAnimation.value.copy(alpha = sequentialAlphaAnimation.value))
        }
    )


    val concurrentColorAnimation = remember { Animatable(Green) }
    val concurrentAlphaAnimation = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        launch { concurrentColorAnimation.animateTo(Red, tween(2000)) }
        launch { concurrentAlphaAnimation.animateTo(0.1f, tween(2000)) }

    }
    Box(modifier = Modifier
        .size(200.dp)
        .drawBehind {
            drawRect(concurrentColorAnimation.value.copy(alpha = concurrentAlphaAnimation.value))
        }
    )
}