package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.eslirodrigues.tutorials.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun ShapeShifterAnimatedVectorDrawable() {
    val animatedVector = AnimatedImageVector.animatedVectorResource(R.drawable.ic_home_animated)
    var isAtEnd by remember { mutableStateOf(false) }
    Image(
        rememberAnimatedVectorPainter(animatedVector, isAtEnd),
        contentDescription = "Home",
        modifier = Modifier.clickable { isAtEnd = !isAtEnd },
    )
}