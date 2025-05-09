package com.eslirodrigues.tutorials.utils.slider

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeMute
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TutorialSlider() {
    var soundVolume by remember { mutableFloatStateOf(0f) }
    var showUpdateValueDialog by remember { mutableStateOf(false) }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Icon(imageVector = Icons.AutoMirrored.Filled.VolumeMute, contentDescription = "Volume")
        Column {
            Text(text = soundVolume.toInt().toString(), fontSize = 22.sp)
            Slider(
                value = soundVolume,
                onValueChange = { soundVolume = it },
                enabled = true,
                valueRange = 0f..100f,
                onValueChangeFinished = {
                    // ex: viewModel.updateSoundVolume(soundVolume)
                    showUpdateValueDialog = true
                },
                steps = 6
            )
        }
    }
    if (showUpdateValueDialog) {
        AlertDialog(
            title = { Text(text = "Value Updated") },
            confirmButton = {
                Text(text = "OK", modifier = Modifier.clickable { showUpdateValueDialog = false })
            },
            onDismissRequest = { showUpdateValueDialog = false }
        )
    }
}