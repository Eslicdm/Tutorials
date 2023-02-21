package com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.data.model.TutorialStateUser
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.viewmodel.UserViewModel

@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    val state by viewModel.userState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.padding(bottom = 20.dp))
        when {
            !state.errorMsg.isNullOrEmpty() -> { Text(text = state.errorMsg!!, fontSize = 32.sp) }
            state.isLoading -> { CircularProgressIndicator() }
            state.data.isNullOrEmpty() -> { Text(text = "Empty", fontSize = 32.sp) }
            !state.data.isNullOrEmpty() -> {
                val userList = state.data!!
                LazyColumn {
                    items(userList) { user ->
                        Text(text = user.userName!!, fontSize = 32.sp)
                    }
                }
            }
        }
        Button(onClick = {
            viewModel.addUserList(listOf(
                TutorialStateUser("First Carl"),
                TutorialStateUser("First Maria"),
                TutorialStateUser("First John"),
            ))
        }) {
            Text(text = "Add First List")
        }
        Button(onClick = {
            viewModel.addUserList(listOf(
                TutorialStateUser("Second Carl"),
                TutorialStateUser("Second Maria"),
                TutorialStateUser("Second John"),
            ))
        }) {
            Text(text = "Add Second List")
        }
    }
}