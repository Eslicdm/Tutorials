package com.eslirodrigues.tutorials.admob.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eslirodrigues.tutorials.admob.ui.components.AdMobBanner

@Composable
fun AdScreen(onNavClick: () -> Unit) {
    Scaffold(bottomBar = { AdMobBanner() }) {
        Column(Modifier.fillMaxSize()) {
            Button(onClick = { onNavClick() }) {
                Text(text = "Next Screen")
            }
            LazyColumn(Modifier
                .fillMaxSize()
                .padding(it)) {
                item {
                    repeat(40) { number ->
                        Text(text = number.toString())
                    }
                }
            }
        }
    }
}