package com.eslirodrigues.tutorials.shimmer_effect.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TutorialShimmerEffect() {
    fun Modifier.shimmerEffect(): Modifier = composed {
        val colors = listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )
        val transition = rememberInfiniteTransition(label = "shimmer")
        val shimmerAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec = infiniteRepeatable(animation = tween(1000, easing = LinearEasing)),
            label = "shimmer"
        )
        background(
            Brush.linearGradient(
                colors = colors,
                start = Offset.Zero,
                end = Offset(x = shimmerAnimation.value, y = shimmerAnimation.value * 2)
            )
        )
    }

    Box(Modifier
        .border(2.dp, Color.Black)
        .fillMaxWidth()
        .shimmerEffect()
    ) {
        Row(Modifier
            .fillMaxWidth()
            .padding(15.dp)
        ) {
            Box(Modifier
                .size(100.dp)
                .padding(10.dp)
                .shimmerEffect()
            )
            Column(Modifier.padding(top = 15.dp)) {
                Box(modifier = Modifier.size(height = 20.dp, width = 150.dp).shimmerEffect())
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.size(height = 15.dp, width = 80.dp).shimmerEffect())
            }
        }
    }
}