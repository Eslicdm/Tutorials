package com.eslirodrigues.tutorials.swipe.ui.screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TutorialSwipeScreen() {
    val screenSizeDp = LocalConfiguration.current.screenWidthDp.dp
    val swipeableState = rememberSwipeableState(0)
    val screenSizePx = with(LocalDensity.current) { screenSizeDp.toPx() }
    val anchors = mapOf(0f to 0, -screenSizePx to 1, screenSizePx to 2)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Vertical
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) } // x = 0 Vertical
                .size(screenSizeDp)
                .background(Color.DarkGray)
        )
    }
}