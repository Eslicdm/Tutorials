package com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.viewmodel.UserViewModel

@Composable
fun UserScreen(
    viewModel: UserViewModel = viewModel()
) {
    val state by viewModel.userState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            !state.errorMsg.isNullOrEmpty() -> { Text(text = state.errorMsg!!) }

            state.isLoading -> { CircularProgressIndicator() }

            !state.data.isNullOrEmpty() -> {
                val userList = state.data!!
                LazyColumn {
                    items(userList) { user ->
                        Text(text = user)
                    }
                }
            }
        }
    }
}