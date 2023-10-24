package com.eslirodrigues.tutorials.window_manager.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.tutorials.window_manager.ui.viewmodel.TutorialWindowManagerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialWindowManagerScreen(
    windowManagerViewModel: TutorialWindowManagerViewModel = hiltViewModel(),
    onItemClick: (name: String, description: String) -> Unit,
) {
    val list = windowManagerViewModel.getTextList()
    Column {
        LazyColumn {
            items(list) { item ->
                Card(onClick = { onItemClick(item.first, item.second) }) {
                    Text(text = item.first, fontSize = 28.sp)
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
        BoxWithConstraints {
            if (maxWidth < 800.dp) {
                Text(text = "Short Text")
            } else {
                Text(text = "Long Text")
            }
        }
    }

}
