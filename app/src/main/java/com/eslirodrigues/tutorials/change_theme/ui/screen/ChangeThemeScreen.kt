package com.eslirodrigues.tutorials.change_theme.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eslirodrigues.tutorials.change_theme.ui.viewmodel.ChangeThemeViewModel

@Composable
fun ChangeThemeScreen(changeThemeViewModel: ChangeThemeViewModel = hiltViewModel()) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        Button(onClick = { changeThemeViewModel.enableDarkTheme(true) }) {
            Text(text = "Dark Mode")
        }
        Button(onClick = { changeThemeViewModel.enableDarkTheme(false) }) {
            Text(text = "Light Mode")
        }
        Button(onClick = { changeThemeViewModel.enableDarkTheme(isSystemInDarkTheme) }) {
            Text(text = "System Mode")
        }
    }
}