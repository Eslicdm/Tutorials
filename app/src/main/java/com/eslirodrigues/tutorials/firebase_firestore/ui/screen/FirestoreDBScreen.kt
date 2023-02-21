package com.eslirodrigues.tutorials.firebase_firestore.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.firebase_firestore.data.model.FirestoreDBUser
import com.eslirodrigues.tutorials.firebase_firestore.ui.viewmodel.FirestoreDBViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirestoreDBScreen(
    viewModel: FirestoreDBViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userState by viewModel.userState.collectAsStateWithLifecycle()

    var inputName by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        TextField(value = inputName, onValueChange = { inputName = it }, label = { Text("Name") })
        Button(onClick = {
            viewModel.createUser(FirestoreDBUser(name = inputName), context = context)
        }) { Text("Submit") }
        when {
            userState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            !userState.errorMsg.isNullOrEmpty() -> {
                Text(text = userState.errorMsg!!)
            }
            userState.data.isNullOrEmpty() -> { Text(text = "Empty") }
            !userState.data.isNullOrEmpty() -> {
                LazyColumn {
                    items(userState.data!!) { item ->
                        FirestoreDBListItem(
                            userName = item?.name!!,
                            userId = item.id!!,
                            onUpdateClick = { id, name ->
                                viewModel.updateUser(FirestoreDBUser(id, name), context)
                            },
                            onDeleteClick = { id, name ->
                                viewModel.deleteUser(FirestoreDBUser(id, name), context)
                            }
                        )
                    }
                }
            }
        }
    }
}