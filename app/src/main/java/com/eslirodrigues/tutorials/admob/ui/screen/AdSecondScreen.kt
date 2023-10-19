package com.eslirodrigues.tutorials.admob.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eslirodrigues.tutorials.admob.ui.components.AdMobBanner

@Composable
fun AdSecondScreen(onPopToMainScreenClick: () -> Unit) {
    // see the scafold materail 3 import, when is in material the app bar do not hide the content
    Scaffold(topBar = { AdMobBanner() }) {
        Column(Modifier.fillMaxSize().padding(it)) {
            Button(onClick = { onPopToMainScreenClick() }) {
                Text(text = "Back to Main Screen")
            }
        }
    }
}