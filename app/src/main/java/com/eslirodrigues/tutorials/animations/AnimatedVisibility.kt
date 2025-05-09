package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnimatedVisibility() {
    var isVisible by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    androidx.compose.animation.AnimatedVisibility(
        visible = isVisible,
        enter = expandIn() + fadeIn(),
        exit = shrinkOut() + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Red)
                .clickable {
                    scope.launch {
                        isVisible = !isVisible
                        delay(500L)
                        isVisible = !isVisible
                    }
                }
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Green)
                    .align(Alignment.Center)
                    .animateEnterExit()
            )
        }
    }


    val visibleState = remember { MutableTransitionState(false).apply { targetState = true } }
    androidx.compose.animation.AnimatedVisibility(
        visibleState = visibleState,
        enter = expandIn() + fadeIn(tween(1000)),
        exit = shrinkOut() + fadeOut(tween(1000))
    ) { Box(modifier = Modifier.size(200.dp).background(Red)) }

    TextButton(onClick = { visibleState.targetState = !visibleState.currentState }) {
        Text(
            text = when {
                visibleState.isIdle && visibleState.currentState -> "Visible"
                !visibleState.isIdle && visibleState.currentState -> "Disappearing"
                visibleState.isIdle && !visibleState.currentState -> "Invisible"
                else -> "Appearing"
            },
            fontSize = 28.sp
        )
    }
}