package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonAndCheckboxScreen() {
    val list = mutableListOf("car", "arm", "dog", "sky")

    var selectedRadioItem by remember { mutableStateOf(list[0]) }

    Column {
        list.forEach { item ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = item)
                RadioButton(selected = item == selectedRadioItem, onClick = { selectedRadioItem = item })
            }
        }
        Spacer(modifier = Modifier.padding(30.dp))
        list.forEach { item ->
            var isSelected by remember { mutableStateOf(false) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = item)
                Checkbox(checked = isSelected, onCheckedChange = { isSelected = it })
            }
        }
    }
}