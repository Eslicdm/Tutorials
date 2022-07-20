package com.eslirodrigues.tutorials.topappbar_compose.ui.screen

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScreen() {
    val topAppBarScrollState = rememberTopAppBarScrollState()

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val exitUntilCollapsedScrollBehavior = remember {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
            decayAnimationSpec = decayAnimationSpec,
            state = topAppBarScrollState
        )
    }
    val enterScrollBehavior = remember { TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarScrollState) }
    val pinnedScrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior(topAppBarScrollState) }

    val scrollableState = rememberScrollState()

    Scaffold(
        modifier = Modifier.nestedScroll(enterScrollBehavior.nestedScrollConnection),
        topBar = {
//            TutorialSmallTopAppBar(enterScrollBehavior)
//            TutorialSmallTopAppBar(pinnedScrollBehavior)
//            TutorialSmallTopAppBar(exitUntilCollapsedScrollBehavior)
//            TutorialMediumTopAppBar(enterScrollBehavior)
//            TutorialMediumTopAppBar(exitUntilCollapsedScrollBehavior)
//            TutorialLargeTopAppBar(enterScrollBehavior)
            TutorialCenterAlignedTopAppBar(enterScrollBehavior)
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues).verticalScroll(scrollableState)) {
            Text("first")
            for (x in 1..50) {
                Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry")
            }
        }
    }
}

@Composable
fun TutorialSmallTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    SmallTopAppBar(
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