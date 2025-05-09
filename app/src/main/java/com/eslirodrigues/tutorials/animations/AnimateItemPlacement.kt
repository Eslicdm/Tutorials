package com.eslirodrigues.tutorials.animations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp

@Composable
fun AnimateItemPlacement() {
    val listNumbers = remember {
        mutableStateListOf("Green" to Green, "Red" to Red, "Blue" to Blue, "Yellow" to Yellow)
    }
    LazyColumn {
        items(listNumbers, key = { it.first }) { color ->
            Box(modifier = Modifier
                .size(height = 100.dp, width = 400.dp)
                .background(color.second)
                .animateItem()
                .clickable { listNumbers.shuffle() }
            )
        }
    }
}