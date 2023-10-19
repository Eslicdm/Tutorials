package com.eslirodrigues.tutorials.state_user_event_viewmodel.ui.event

sealed class CardEvent {
    data object ShowCard: CardEvent()
    data object HideCard: CardEvent()
}