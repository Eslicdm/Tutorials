package com.eslirodrigues.tutorials.sqldelight_database.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.sqldelight_database.ui.viewmodel.SqlDelightViewModel
import tutorialsqldelightdb.SqldelightUserEntity

@Composable
fun SqlDelightUserScreen(
    viewModel: SqlDelightViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userState by viewModel.userState.collectAsStateWithLifecycle()

    var inputName by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        TextField(value = inputName, onValueChange = { inputName = it }, label = { Text("Name") })
        Button(onClick = {
            viewModel.addUser(userId = null, userName = inputName, context = context)
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
                        SqlDelightUserListItem(
                            userName = item.userName,
                            userId = item.id.toString(),
                            onUpdateClick = { id, name ->
                                viewModel.updateUser(SqldelightUserEntity(id.toLong(), name), context)
                            },
                            onDeleteClick = { id, name ->
                                viewModel.deleteUser(SqldelightUserEntity(id.toLong(), name), context)
                            }
                        )
                    }
                }
            }
        }
    }
}