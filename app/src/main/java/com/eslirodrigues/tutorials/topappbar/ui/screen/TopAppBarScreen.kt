package com.eslirodrigues.tutorials.topappbar.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScreen() {
    val exitUntilCollapsedScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val enterScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(enterScrollBehavior.nestedScrollConnection),
        topBar = {
            TutorialTopAppBar(enterScrollBehavior)
//            TutorialTopAppBar(pinnedScrollBehavior)
//            TutorialTopAppBar(exitUntilCollapsedScrollBehavior)
//            TutorialMediumTopAppBar(enterScrollBehavior)
//            TutorialMediumTopAppBar(exitUntilCollapsedScrollBehavior)
//            TutorialLargeTopAppBar(enterScrollBehavior)
//            TutorialCenterAlignedTopAppBar(enterScrollBehavior)
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(50) {
                Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = { Text(text = "Small") },
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
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onTertiary,
            navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
            actionIconContentColor = MaterialTheme.colorScheme.onTertiary,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialMediumTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
)  {
    MediumTopAppBar(
        title = { Text(text = "Medium") },
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
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onTertiary,
            navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
            actionIconContentColor = MaterialTheme.colorScheme.onTertiary,
        )
    )
}

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialCenterAlignedTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
)  {
    CenterAlignedTopAppBar(
        title = { Text(text = "CenterAligned") },
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
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onTertiary,
            navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
            actionIconContentColor = MaterialTheme.colorScheme.onTertiary,
        )
    )
}