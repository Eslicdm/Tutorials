package com.eslirodrigues.tutorials.module.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eslirodrigues.image_feature.coil.TutorialCoilModuleImage

@Composable
fun TutorialModuleScreen() {
    Box(modifier = Modifier.size(400.dp)) {
        TutorialCoilModuleImage()
    }
}