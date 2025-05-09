package com.eslirodrigues.tutorials.utils.top_appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialLargeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
)  {
    LargeTopAppBar(
        title = { Text(text = "Large") },
        navigationIcon = {
            IconButton(onClick = { "show-nav-drawer" }) {
                Icon(Icons.Default.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = { "show-search-bar" }) {
                Icon(Icons.Default.Search, contentDescription = "search")
            }
            IconButton(onClick = { "show-dropdown-menu" }) {
                Icon(Icons.Default.MoreVert, contentDescription = "more")
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onTertiary,
            navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
            actionIconContentColor = MaterialTheme.colorScheme.onTertiary,
        )
    )
}