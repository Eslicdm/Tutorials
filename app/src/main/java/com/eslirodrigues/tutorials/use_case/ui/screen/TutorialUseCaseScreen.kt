package com.eslirodrigues.tutorials.use_case.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.use_case.ui.viewmodel.TutorialUseCaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first

@Composable
fun TutorialUseCaseScreen(
    tutorialUseCaseViewModel: TutorialUseCaseViewModel = hiltViewModel()
) {
    val textState by tutorialUseCaseViewModel.textState.collectAsStateWithLifecycle()
    val textStateIn by tutorialUseCaseViewModel.textStateIn.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = textState.data?.first() ?: "Empty", fontSize = 28.sp)
        Text(text = textStateIn.joinToString(), fontSize = 28.sp)
    }
}