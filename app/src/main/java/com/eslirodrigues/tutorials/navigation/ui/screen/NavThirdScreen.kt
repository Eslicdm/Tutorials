package com.eslirodrigues.tutorials.navigation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.eslirodrigues.tutorials.navigation.ui.navigation.NavRoute

@Composable
fun ThirdScreen(navController: NavController) {
    Column {
        Text(text = "ThirdScreen")
        Button(
            onClick = {
//                navController.popBackStack()
                navController.popBackStack(NavRoute.NavSecondScreen.route, inclusive = false)
            }
        ) {
            Text(text = "SecondScreen")
        }
        Button(
            onClick = {
//                navController.popBackStack(NavRoute.NavMainScreen.route, inclusive = false)
                navController.navigate(NavRoute.NavMainScreen.route)
            }
        ) {
            Text(text = "MainScreen")
        }
    }
}