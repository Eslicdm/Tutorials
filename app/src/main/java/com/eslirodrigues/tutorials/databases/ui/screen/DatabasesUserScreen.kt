package com.eslirodrigues.tutorials.databases.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser
import com.eslirodrigues.tutorials.databases.room.RoomUser
import com.eslirodrigues.tutorials.databases.ui.viewmodel.DatabasesUserViewModel

@Composable
fun DatabasesUserScreen(
    viewModel: DatabasesUserViewModel = hiltViewModel()
) {
    val userState by viewModel.userState.collectAsStateWithLifecycle()

    var inputName by remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        Column(Modifier.fillMaxSize().padding(paddingValues)) {
            TextField(value = inputName, onValueChange = { inputName = it }, label = { Text("Name") })
            Button(onClick = {
                viewModel.addUser(DatabasesUser(userName = inputName))
            }) { Text("Submit") }
            when {
                userState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                !userState.errorMsg.isNullOrEmpty() -> {
                    Text(text = userState.errorMsg ?: "Error")
                }
                userState.data.isNullOrEmpty() -> { Text(text = "Empty") }
                userState.data != null -> {
                    LazyColumn {
                        items(userState.data!!) { item ->
                            DatabasesUserListItem(
                                userName = item.userName,
                                userId = item.id.toString(),
                                onUpdateClick = { id, name ->
                                    viewModel.updateUser(DatabasesUser(id.toInt(), name))
                                },
                                onDeleteClick = { id, name ->
                                    viewModel.deleteUser(DatabasesUser(id.toInt(), name))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}