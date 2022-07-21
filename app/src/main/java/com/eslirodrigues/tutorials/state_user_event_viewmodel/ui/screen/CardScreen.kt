package com.eslirodrigues.tutorials.state_user_event_viewmodel.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eslirodrigues.tutorials.state_user_event_viewmodel.ui.event.CardEvent
import com.eslirodrigues.tutorials.state_user_event_viewmodel.ui.viewmodel.CardViewModel

@Composable
fun CardScreen(cardViewModel: CardViewModel = viewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val cardState by cardViewModel.cardState.collectAsState()

        Box(modifier = Modifier.size(width = 60.dp, height = 90.dp)
            .background(
                if (cardState.showCard) {
                    Color.Transparent
                } else {
                    Color.Black
                }
            )
        ) {
            Text(text = cardState.data, fontSize = 52.sp)
        }

        Button(
            onClick = {
                if (cardState.showCard) {
                    cardViewModel.cardEvent(CardEvent.HideCard)
                } else {
                    cardViewModel.cardEvent(CardEvent.ShowCard)
                }
            }
        ) {
            Text(text = "change")
        }
    }
}