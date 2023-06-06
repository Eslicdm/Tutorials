package com.eslirodrigues.tutorials.network_state.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.eslirodrigues.tutorials.network_state.network.TutorialNetworkState
import com.eslirodrigues.tutorials.network_state.ui.viewmodel.TutorialNetworkStateViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun TutorialNetworkStateScreen(
    networkStateViewModel: TutorialNetworkStateViewModel = hiltViewModel()
) {
    val networkState by networkStateViewModel.networkState.collectAsStateWithLifecycle()
    var networkStateText by remember { mutableStateOf("") }
    networkStateText = when(networkState) {
        is TutorialNetworkState.Available -> "Available"
        is TutorialNetworkState.Losing -> "Losing"
        is TutorialNetworkState.Lost -> "Lost"
        is TutorialNetworkState.Unavailable -> "Unavailable"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = networkStateText)
    }
}