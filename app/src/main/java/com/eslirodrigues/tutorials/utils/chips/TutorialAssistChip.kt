package com.eslirodrigues.tutorials.utils.chips

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TutorialAssistChip() {
    val assistList = listOf(
        TutorialChipsModel(
            name = "Call to my friend",
            leadingIcon = Icons.Default.Face
        ),
        TutorialChipsModel(
            name = "Share the item",
            leadingIcon = Icons.Default.Share
        )
    )

    var selectedItem by remember { mutableStateOf("") }
    var isSelected by remember { mutableStateOf(false) }

    LazyRow {
        items(assistList) { item ->
            isSelected = selectedItem == item.name
            Spacer(modifier = Modifier.padding(5.dp))
            AssistChip(
                onClick = {
                    selectedItem = if (selectedItem != item.name) item.name else ""
                },
                label = {
                    Text(text = item.name)
                },
                leadingIcon = {
                    if (item.leadingIcon != null)
                        Icon(item.leadingIcon, contentDescription = "")
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = if (isSelected)
                        MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                    labelColor = Color.Black,
                    leadingIconContentColor = if (isSelected)
                        MaterialTheme.colorScheme.onPrimaryContainer else Color.LightGray,
                )
            )
        }
    }
}