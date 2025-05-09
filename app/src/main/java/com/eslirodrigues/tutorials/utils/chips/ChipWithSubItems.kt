package com.eslirodrigues.tutorials.utils.chips

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipWithSubItems(chipLabel: String, chipItems: List<String>) {
    var isSelected by remember { mutableStateOf(false) }
    var showSubList by remember { mutableStateOf(false) }
    var filterName by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = showSubList,
        onExpandedChange = { showSubList = !showSubList }
    ) {
        FilterChip(
            modifier = Modifier.menuAnchor(),
            selected = isSelected,
            onClick = {
                isSelected = true
            },
            label = { Text(text = filterName.ifEmpty { chipLabel }) },
            trailingIcon = {
                Icon(
                    modifier = Modifier.rotate(if (showSubList) 180f else 0f),
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "List"
                )
            }
        )
        ExposedDropdownMenu(
            expanded = showSubList,
            onDismissRequest = { showSubList = false },
        ) {
            chipItems.forEach { subListItem ->
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        filterName = subListItem
                        showSubList = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (subListItem == filterName || subListItem == chipLabel) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else { Color.Transparent }
                    )
                ) {
                    Text(text = subListItem, color = Color.Black)
                }
            }
        }
    }
}