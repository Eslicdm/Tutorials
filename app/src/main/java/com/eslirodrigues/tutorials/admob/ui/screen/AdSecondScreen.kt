package com.eslirodrigues.tutorials.admob.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.admob.ui.components.AdMobBanner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdSecondScreen(navController: NavController) {
    Scaffold(topBar = { AdMobBanner() }) {
        Column(Modifier
            .fillMaxSize()
            .padding(it)) {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back to Main Screen")
            }
        }
    }
}