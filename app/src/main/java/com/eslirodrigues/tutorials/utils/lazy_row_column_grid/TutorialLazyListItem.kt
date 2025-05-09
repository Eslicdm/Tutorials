package com.eslirodrigues.tutorials.utils.lazy_row_column_grid

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.eslirodrigues.tutorials.utils.lazy_row_column_grid.TestTagsConstants.INDEX_CAT_NUMBER

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
            modifier = Modifier.align(Alignment.CenterHorizontally).testTag(INDEX_CAT_NUMBER)
        )
    }
}