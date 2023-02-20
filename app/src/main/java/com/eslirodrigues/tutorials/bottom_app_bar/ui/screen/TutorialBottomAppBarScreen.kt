package com.eslirodrigues.tutorials.bottom_app_bar.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialBottomAppBarScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Mic, contentDescription = "Mic")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /*TODO*/ },
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) {

    }
}