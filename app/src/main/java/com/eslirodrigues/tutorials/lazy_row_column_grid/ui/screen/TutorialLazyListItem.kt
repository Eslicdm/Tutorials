package com.eslirodrigues.tutorials.lazy_row_column_grid.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialLazyListItem(
    name: String,
    number: Int
) {
    Card(modifier = Modifier.size(width = 150.dp, height = 100.dp).padding(10.dp)) {
        Spacer(modifier = Modifier.padding(top = 15.dp))
        Text(
            text = "Name: $name",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Number: $number",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}