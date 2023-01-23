package com.eslirodrigues.tutorials.di_koin.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.eslirodrigues.tutorials.di_koin.ui.viewmodel.KoinViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named


@Composable
fun KoinScreen(
    koinViewModel: KoinViewModel = koinViewModel(),
    stringFour: String = get(named("stringFour")),
    stringFive: String = get(named("stringFive"))
) {
    val dependencyString = koinViewModel.dependencyString
    val stringOne = koinViewModel.stringOne
    val stringTwo = koinViewModel.stringTwo

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = dependencyString, fontSize = 32.sp)
        Text(text = stringOne, fontSize = 32.sp)
        Text(text = stringTwo, fontSize = 32.sp)
        Text(text = stringFour, fontSize = 32.sp)
        Text(text = stringFive, fontSize = 32.sp)
    }
}