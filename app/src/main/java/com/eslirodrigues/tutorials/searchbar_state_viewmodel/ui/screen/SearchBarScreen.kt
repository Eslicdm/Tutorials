package com.eslirodrigues.tutorials.searchbar_state_viewmodel.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eslirodrigues.tutorials.R.*
import com.eslirodrigues.tutorials.searchbar_state_viewmodel.ui.viewmodel.SearchBarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarScreen(searchBarViewModel: SearchBarViewModel = viewModel()) {
    val state by searchBarViewModel.dogState.collectAsState()

    var showSearchBar by remember { mutableStateOf(false) }
    var searchInputText by remember { mutableStateOf("") }

    Scaffold(topBar = {
        SmallTopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            title = { Text(text = stringResource(id = string.app_name)) },
            actions = {
                if (showSearchBar) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(8.dp),
                            value = searchInputText,
                            onValueChange = {
                                searchInputText = it
                                searchBarViewModel.searchDog(it)
                            },
                            label = {
                                Text(text = "Search")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                )
                            },
                            maxLines = 1,
                            singleLine = true
                        )
                        IconButton(
                            onClick = {
                                showSearchBar = false
                            },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }
                        BackHandler {
                            showSearchBar = false
                        }
                    }
                } else {
                    IconButton(onClick = { showSearchBar = !showSearchBar }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                }
            }
        )
    }) {
        when {
            !state.data.isNullOrEmpty() -> {
                LazyColumn(contentPadding = it) {
                    items(state.data!!) { dogs ->
                        Text(text = dogs)
                    }
                }
            }
        }
    }

}