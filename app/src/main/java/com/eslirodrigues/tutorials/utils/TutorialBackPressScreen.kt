package com.eslirodrigues.tutorials.utils

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TutorialBackPressScreen(activity: Activity) {
    var value by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.error),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BackHandler(enabled = value >= 5) {
            value = 0
            activity.finishAndRemoveTask()
        }
        Button(onClick = { value++ }) { Text(text = "$value") }
    }
}