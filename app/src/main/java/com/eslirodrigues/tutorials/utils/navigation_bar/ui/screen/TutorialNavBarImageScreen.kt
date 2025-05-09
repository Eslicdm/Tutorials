package com.eslirodrigues.tutorials.utils.navigation_bar.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.utils.navigation_bar.ui.components.TutorialNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TutorialNavBarImageScreen(navController: NavController) {
    Scaffold(
        bottomBar = { TutorialNavigationBar(navController = navController) }
    ) {
        Text(text = "Image", fontSize = 32.sp)
    }
}