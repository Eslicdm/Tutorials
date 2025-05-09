package com.eslirodrigues.tutorials.utils.swipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

private enum class VerticalDragValue { Settled, TopToBottom, BottomToTop }
private enum class HorizontalDragValue { Settled, StartToEnd, EndToStart }

@Composable
fun SwipeScreen() {
    TutorialDynamicSwipe()
    TutorialStaticSwipe()
}

@Composable
fun TutorialDynamicSwipe() {
    var boxWidth by remember { mutableFloatStateOf(0f) }
    val state = remember {
        AnchoredDraggableState(
            initialValue = HorizontalDragValue.Settled
        )
//        AnchoredDraggableState(
//            initialValue = HorizontalDragValue.Settled,
//            positionalThreshold = { distance -> distance * 0.3f },
//            velocityThreshold = { 0.3f },
//            animationSpec = tween(),
//        )
    }
    val anchors = DraggableAnchors {
        HorizontalDragValue.Settled at 0f
        HorizontalDragValue.StartToEnd at boxWidth / 3
        HorizontalDragValue.EndToStart at -boxWidth / 3
    }
    SideEffect { state.updateAnchors(anchors) }
    Box(
        Modifier
            .graphicsLayer { boxWidth = size.width }
            .offset { IntOffset(x = state.requireOffset().roundToInt(), y = 0) }
            .height(400.dp)
            .width(200.dp)
            .anchoredDraggable(state, Orientation.Horizontal)
            .background(Color.LightGray)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialStaticSwipe() {
    val density = LocalDensity.current
    val screenSizeDp = LocalWindowInfo.current.containerSize.width.dp
    val screenSizePx = with(density) { screenSizeDp.toPx() }
    val anchors = DraggableAnchors {
        VerticalDragValue.Settled at 0f
        VerticalDragValue.TopToBottom at screenSizePx / 2
        VerticalDragValue.BottomToTop at -screenSizePx / 2
    }
    val state = remember {
        AnchoredDraggableState(
            initialValue = VerticalDragValue.Settled,
        )
//        AnchoredDraggableState(
//            initialValue = VerticalDragValue.Settled,
//            anchors = anchors,
//            positionalThreshold = { distance -> distance * 0.3f },
//            velocityThreshold = { 0.3f },
//            animationSpec = tween(),
//        )
    }
    Box(
        Modifier
            .offset { IntOffset(x = 0, y = state.requireOffset().roundToInt()) }
            .size(screenSizeDp)
            .anchoredDraggable(state, Orientation.Vertical)
            .background(Color.DarkGray)
    )
}