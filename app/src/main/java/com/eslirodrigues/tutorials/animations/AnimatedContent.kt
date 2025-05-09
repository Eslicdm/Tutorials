package com.eslirodrigues.tutorials.animations

import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedContent() {
    var targetCountState by remember { mutableIntStateOf(0) }
    androidx.compose.animation.AnimatedContent(
        targetState = targetCountState,
        transitionSpec = { ((expandVertically()).togetherWith(shrinkVertically())) },
        label = "Increase Count"
    ) { targetInt ->
        TextButton(onClick = { targetCountState++ }) {
            Text(text = targetInt.toString(), fontSize = 28.sp)
        }
    }
}