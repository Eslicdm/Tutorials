package com.eslirodrigues.tutorials.navigation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TutorialNavThirdScreen(
    onNavClick: () -> Unit,
    onPopBackStackClick: () -> Unit
) {
    Column {
        Text(text = "ThirdScreen")
        Button(onClick = { onPopBackStackClick() }) {
            Text(text = "SecondScreen")
        }
        Button(onClick = { onNavClick() }) {
            Text(text = "MainScreen")
        }
    }
}