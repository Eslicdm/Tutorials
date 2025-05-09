package com.eslirodrigues.tutorials.utils.chips

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TutorialInputChip() {
    val inputList = listOf(
        TutorialChipsModel(
            name = "Crowley Junior",
            textExpanded = "crowleyjunir@gmail.com",
            leadingIcon = Icons.Default.Person,
            trailingIcon = Icons.Default.Close
        ),
        TutorialChipsModel(
            name = "John Dee",
            textExpanded = "johndee@gmail.com",
            leadingIcon = Icons.Default.PersonAdd,
            trailingIcon = Icons.Default.Close
        )
    )

    val selectedItems = remember { mutableStateListOf<String>() }
    var isTextExpanded by remember { mutableStateOf(false) }

    LazyRow {
        items(inputList) { item ->
            isTextExpanded = selectedItems.contains(item.name)
            Spacer(modifier = Modifier.padding(5.dp))
            if (isTextExpanded && item.textExpanded != null) {
                Text(text = item.textExpanded)
            } else {
                InputChip(
                    selected = true,
                    onClick = { selectedItems.add(item.name) },
                    label = { Text(text = item.name) },
                    avatar = {
                        if (item.leadingIcon != null)
                            Icon(item.leadingIcon, contentDescription = item.name)
                    },
                    trailingIcon = {
                        if (item.trailingIcon != null)
                            Icon(item.trailingIcon, contentDescription = item.name)
                    }
                )
            }
        }
    }
}