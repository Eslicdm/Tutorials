package com.eslirodrigues.tutorials.room_database.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
import com.eslirodrigues.tutorials.room_database.ui.viewmodel.RoomUserViewModel

@Composable
fun RoomUserScreen(
    viewModel: RoomUserViewModel = hiltViewModel()
) {
    val userState by viewModel.userState.collectAsState()

    var inputName by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        TextField(value = inputName, onValueChange = { inputName = it }, label = { Text("Name") })
        Button(onClick = {
            viewModel.addUser(RoomUser(userName = inputName))
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
                        RoomUserListItem(
                            userName = item.userName,
                            userId = item.id.toString(),
                            onUpdateClick = { id, name ->
                                viewModel.updateUser(RoomUser(id.toInt(), name))
                            },
                            onDeleteClick = { id, name ->
                                viewModel.deleteUser(RoomUser(id.toInt(), name))
                            }
                        )
                    }
                }
            }
        }
    }
}