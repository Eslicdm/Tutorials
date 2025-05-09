package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp

@Composable
fun CrossFade() {
    var targetState by remember { mutableIntStateOf(0) }
    Crossfade(
        targetState = targetState,
        animationSpec = tween(durationMillis = 1000),
        label = "Change Box"
    ) { targetInt ->
        when (targetInt) {
            0 -> Box(modifier = Modifier.size(50.dp).background(Red).clickable { targetState = 1 })
            1 -> Box(modifier = Modifier.size(50.dp).background(Blue).clickable { targetState = 0 })
        }
    }
}