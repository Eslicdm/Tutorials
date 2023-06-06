package com.eslirodrigues.tutorials.network_state.network

sealed interface TutorialNetworkState {
    object Available : TutorialNetworkState
    object Lost : TutorialNetworkState
    object Losing : TutorialNetworkState
    object Unavailable : TutorialNetworkState
}