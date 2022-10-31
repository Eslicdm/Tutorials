package com.eslirodrigues.tutorials.navigation_bar.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.navigation_bar.ui.components.TutorialNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialNavBarMusicScreen(navController: NavController) {
    Scaffold(
        bottomBar = { TutorialNavigationBar(navController = navController) }
    ) {
        Text(text = "Music", fontSize = 32.sp)
    }
}