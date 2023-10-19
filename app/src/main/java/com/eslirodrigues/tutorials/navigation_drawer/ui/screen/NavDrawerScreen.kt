package com.eslirodrigues.tutorials.navigation_drawer.ui.screen

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.navigation_drawer.ui.components.DrawerContent
import com.eslirodrigues.tutorials.navigation_drawer.ui.components.NavDrawer
import com.eslirodrigues.tutorials.navigation_drawer.ui.components.NavTopAppBar
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavDrawerScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController = navController, scope = scope, drawerState = drawerState)
        }
    ) {
        Scaffold(
            topBar = {
                NavTopAppBar(
                    title = "NavDrawer",
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "menu")
                        }
                    }
                )
            }
        ) {

        }
    }
}





