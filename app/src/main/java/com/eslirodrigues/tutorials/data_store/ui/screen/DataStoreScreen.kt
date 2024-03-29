package com.eslirodrigues.tutorials.data_store.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.data_store.ui.viewmodel.DataStoreViewModel

@Composable
fun DataStoreScreen(viewModel: DataStoreViewModel = hiltViewModel()) {
    val name by viewModel.readName.collectAsStateWithLifecycle()

    var nameInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = nameInput, onValueChange = { nameInput = it })
        Button(onClick = { viewModel.saveName(nameInput) }) {
            Text(text = "Submit")
        }
        Text(text = name, fontSize = 32.sp)
    }
}