package com.eslirodrigues.tutorials.navigation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.navigation.ui.navigation.NavRoute

@Composable
fun SecondScreen(
    onNavClick: () -> Unit,
    onPopBackStackClick: () -> Unit,
    name: String,
    isOverEighteen: Boolean
) {
    Column {
        Text(text = "Second Screen")
        Text(text = "$name is over 18? ${if (isOverEighteen) "Yes" else "No"}")
        Row {
            Button(onClick = { onPopBackStackClick() }) {
                Text(text = "MainScreen")
            }
            Button(onClick = { onNavClick() }) {
                Text(text = "ThirdScreen")
            }
        }
    }
}