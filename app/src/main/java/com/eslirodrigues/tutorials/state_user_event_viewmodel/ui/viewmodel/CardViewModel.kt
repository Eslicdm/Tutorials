package com.eslirodrigues.tutorials.state_user_event_viewmodel.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.eslirodrigues.tutorials.state_user_event_viewmodel.ui.event.CardEvent
import com.eslirodrigues.tutorials.state_user_event_viewmodel.ui.state.CardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CardViewModel : ViewModel() {

    private val _cardState = MutableStateFlow(CardState())
    val cardState: StateFlow<CardState> = _cardState.asStateFlow()

    init {
        val cardNumber = "2"
        _cardState.update { it.copy(data = cardNumber, showCard = false) }
    }

    fun cardEvent(event: CardEvent) {
        when (event) {
            is CardEvent.HideCard -> {
                _cardState.update { it.copy(showCard = false) }
            }
            is CardEvent.ShowCard -> {
                _cardState.update { it.copy(showCard = true) }
            }
        }
    }
}