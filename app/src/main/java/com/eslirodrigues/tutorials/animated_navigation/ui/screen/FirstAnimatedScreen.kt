package com.eslirodrigues.tutorials.animated_navigation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.animated_navigation.ui.navigation.NavAnimatedRoute

@Composable
fun FirstAnimatedScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = { navController.navigate(NavAnimatedRoute.SecondScreen.route) }) {
            Text(text = "FirstScreen", fontSize = 32.sp)
        }
    }
}