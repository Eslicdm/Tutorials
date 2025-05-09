package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScrollableColumnRowScreen() {
    val columnScrollableState = rememberScrollState()
    val rowScrollableState = rememberScrollState()

    Column {
        Row(modifier = Modifier
            .horizontalScroll(rowScrollableState)
        ) {
            repeat(10) {
                Icon(Icons.Default.AddCircle, contentDescription = "circle", modifier = Modifier.size(200.dp))
            }
        }
        Column(modifier = Modifier
            .verticalScroll(columnScrollableState, reverseScrolling = true)
        ) {
            Text("first")
            repeat(10) {
                Row {
                    Icon(Icons.Default.Face, contentDescription = "face", modifier = Modifier.size(200.dp))
                    if (columnScrollableState.isScrollInProgress) {
                        Text(text = "Scrolling")
                    }
                }
            }
        }
    }
}