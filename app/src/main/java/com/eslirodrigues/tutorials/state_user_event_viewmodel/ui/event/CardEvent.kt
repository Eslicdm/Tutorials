package com.eslirodrigues.tutorials.state_user_event_viewmodel.ui.event

sealed class CardEvent {
    object ShowCard: CardEvent()
    object HideCard: CardEvent()
}