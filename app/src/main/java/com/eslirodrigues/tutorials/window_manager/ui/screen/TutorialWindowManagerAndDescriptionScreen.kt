package com.eslirodrigues.tutorials.window_manager.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun TutorialWindowManagerAndDescriptionScreen() {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Row {
        TutorialWindowManagerScreen(onItemClick = { first, second ->
            name = first
            description = second
        })
        TutorialWindowManagerDescriptionScreen(name, description)
    }
}