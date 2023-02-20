package com.eslirodrigues.tutorials.navigation_drawer.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavTopAppBar(
    title: String = "",
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = navigationIcon,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onTertiary,
            navigationIconContentColor = MaterialTheme.colorScheme.onTertiary,
            actionIconContentColor = MaterialTheme.colorScheme.onTertiary,
        )
    )
}