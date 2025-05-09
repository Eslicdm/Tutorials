package com.eslirodrigues.tutorials.utils.chips

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TutorialSuggestionChip() {
    val suggestionList = listOf(
        TutorialChipsModel(
            name = "Country Name",
            leadingIcon = Icons.Outlined.Public
        ),
        TutorialChipsModel(
            name = "Art Name",
            leadingIcon = Icons.Outlined.Palette
        )
    )

    LazyRow {
        items(suggestionList) { item ->
            Spacer(modifier = Modifier.padding(5.dp))
            SuggestionChip(
                enabled = suggestionList[0].name == item.name,
                onClick = { /*TODO*/ },
                label = { Text(text = item.name) },
                icon = {
                    if (item.leadingIcon != null)
                        Icon(item.leadingIcon, contentDescription = item.name)
                }
            )
        }
    }
}