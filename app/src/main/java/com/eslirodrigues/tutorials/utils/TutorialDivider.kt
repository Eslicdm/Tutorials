package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TutorialDivider() {
    val list = listOf("Cat", "Dog", "Bird", "Goat")
    LazyColumn {
        items(list) { item ->
            Text(text = item)
            HorizontalDivider(thickness = 3.dp)
        }
    }
}