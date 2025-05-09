package com.eslirodrigues.tutorials.utils.chips

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
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
fun TutorialFilterChip() {
    val filterList = listOf(
        TutorialChipsModel(
            name = "Cart",
            leadingIcon = Icons.Default.ShoppingCart,
        ),
        TutorialChipsModel(
            name = "Phone",
            subList = listOf("Asus", "Pixel", "Apple"),
            trailingIcon = Icons.Default.ArrowDropDown,
            leadingIcon = Icons.Default.Check
        ),
        TutorialChipsModel(
            name = "Tablet",
            leadingIcon = Icons.Default.Check
        ),
        TutorialChipsModel(
            name = "Dog",
            trailingIcon = Icons.Default.Close
        )
    )

    val selectedItems = remember { mutableStateListOf<String>() }
    var isSelected by remember { mutableStateOf(false) }

    LazyRow {
        items(filterList) { item ->
            isSelected = selectedItems.contains(item.name)
            Spacer(modifier = Modifier.padding(5.dp))
            if (item.subList != null) {
                ChipWithSubItems(chipLabel = item.name, chipItems = item.subList)
            } else {
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        when (selectedItems.contains(item.name)) {
                            true -> selectedItems.remove(item.name)
                            false -> selectedItems.add(item.name)
                        }
                    },
                    label = { Text(text = item.name) },
                    leadingIcon = {
                        val isCheckIcon = item.leadingIcon == Icons.Default.Check
                        if (item.leadingIcon != null && isCheckIcon && isSelected) {
                            Icon(item.leadingIcon, contentDescription = item.name)
                        }
                        if (item.leadingIcon != null && !isCheckIcon) {
                            Icon(item.leadingIcon, contentDescription = item.name)
                        }
                    },
                    trailingIcon = {
                        if (item.trailingIcon != null && isSelected)
                            Icon(item.trailingIcon, contentDescription = item.name)
                    }
                )
            }
        }
    }
}