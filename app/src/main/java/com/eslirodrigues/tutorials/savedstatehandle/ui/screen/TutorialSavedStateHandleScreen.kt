package com.eslirodrigues.tutorials.savedstatehandle.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eslirodrigues.tutorials.savedstatehandle.ui.viewmodel.TutorialSavedStateHandleViewModel

@Composable
fun TutorialSavedStateHandleScreen(
    viewModel: TutorialSavedStateHandleViewModel = hiltViewModel()
) {
    val list = listOf("Option 1", "Option 2", "Option 3")
    var selectedRadioItem by rememberSaveable { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "saved value: ${viewModel.savedOption}")
        list.forEach { item ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = item)
                RadioButton(
                    selected = item == selectedRadioItem,
                    onClick = {
                        selectedRadioItem = item
                        viewModel.saveOption(item)
                    }
                )
            }
        }
    }
}