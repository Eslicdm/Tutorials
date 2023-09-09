package com.eslirodrigues.tutorials.navigation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.navigation.ui.navigation.NavRoute

@Composable
fun NavMainScreen(
    onNavClick: (name: String, isOverEighteen: Boolean) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var isOverEighteen by remember { mutableStateOf(false) }

    Column {
        Text(text = "MainScreen")
        TextField(value = name, onValueChange = { name = it }, label = { Text(text = "Name") })
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Yes, i'm over the age of 18")
            Checkbox(checked = isOverEighteen, onCheckedChange = { isOverEighteen = !isOverEighteen })
        }

        Button(onClick = { if (name.isNotEmpty()) { onNavClick(name, isOverEighteen) } }) {
            Text(text = "SecondScreen")
        }
    }

}