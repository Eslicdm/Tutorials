package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp

@Composable
fun MultipleAnimations() {
    var isExpanded by remember { mutableStateOf(false) }
    val transition = updateTransition(isExpanded, label = "box state")
    val animatedSize by transition.animateDp(label = "box size") { expandedState ->
        if (expandedState) 200.dp else 100.dp
    }
    val animatedColor by transition.animateColor(label = "box color") { expandedState ->
        if (expandedState) Red else Green
    }
    Box(modifier = Modifier
        .background(animatedColor)
        .size(animatedSize)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { isExpanded = !isExpanded }
    )

    transition.AnimatedVisibility(visible = { isExpanded }) {
        Box(modifier = Modifier.size(100.dp).background(Blue))
    }
    transition.AnimatedContent { expandedState ->
        Box(modifier = Modifier.size(100.dp).background(if (expandedState) Yellow else DarkGray))
    }
}