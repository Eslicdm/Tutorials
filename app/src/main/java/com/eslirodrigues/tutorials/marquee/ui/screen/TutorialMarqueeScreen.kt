package com.eslirodrigues.tutorials.marquee.ui.screen

import androidx.compose.foundation.DefaultMarqueeDelayMillis
import androidx.compose.foundation.DefaultMarqueeSpacing
import androidx.compose.foundation.DefaultMarqueeVelocity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialMarqueeScreen() {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.basicMarquee(
                iterations = Int.MAX_VALUE,
                animationMode = MarqueeAnimationMode.Immediately,
                delayMillis = DefaultMarqueeDelayMillis, // 1200
                initialDelayMillis = DefaultMarqueeDelayMillis, // 1200
                spacing = DefaultMarqueeSpacing, // MarqueeSpacing.fractionOfContainer(1f / 3f)
                velocity = DefaultMarqueeVelocity // 30.dp
            ),
            text = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        )
        Text(
            modifier = Modifier
                .basicMarquee(
                    iterations = 1,
                    animationMode = MarqueeAnimationMode.WhileFocused,
                    delayMillis = 0,
                    initialDelayMillis = 500,
                    spacing = MarqueeSpacing.fractionOfContainer(1f / 1f),
                    velocity = 60.dp
                )
                .focusRequester(focusRequester)
                .focusable()
                .clickable { focusRequester.requestFocus() },
            text = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        )
    }
}