package com.eslirodrigues.tutorials.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun TutorialProgressIndicatorScreen() {
    var downloadProgress by remember { mutableFloatStateOf(0.1f) }
    val animatedDownloadProgress by animateFloatAsState(
        targetValue = downloadProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "change progress"
    )

    LaunchedEffect(downloadProgress) {
        while (downloadProgress < 1f) {
            delay(1000L)
            downloadProgress += 0.1f
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        LinearProgressIndicator(progress = { animatedDownloadProgress })
    }
}