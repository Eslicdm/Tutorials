package com.eslirodrigues.tutorials.swipe.ui.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class DragValue { Start, Center, End }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialSwipeScreen() {
    val density = LocalDensity.current
    val screenSizeDp = LocalConfiguration.current.screenWidthDp.dp
    val screenSizePx = with(density) { screenSizeDp.toPx() }
    val anchors = remember {
        DraggableAnchors {
            DragValue.Start at 0f
            DragValue.Center at screenSizePx / 2
            DragValue.End at screenSizePx
        }
    }
    val state = remember {
        AnchoredDraggableState(
            initialValue = DragValue.Start,
            positionalThreshold = { distance -> distance * 0.3f },
            velocityThreshold = {  with(density) { 100.dp.toPx() }  },
            animationSpec = tween(),
        )
    }
    SideEffect { state.updateAnchors(anchors) }
    Box(
        Modifier
            .fillMaxSize()
            .anchoredDraggable(state, Orientation.Vertical)
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(x = 0, y = state.requireOffset().roundToInt()) } // x = 0 Horizontal
                .size(screenSizeDp)
                .background(Color.DarkGray)
        )
    }
}