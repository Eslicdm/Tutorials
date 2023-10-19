package com.eslirodrigues.tutorials.network_state.network

sealed interface TutorialNetworkState {
    data object Available : TutorialNetworkState
    data object Lost : TutorialNetworkState
    data object Losing : TutorialNetworkState
    data object Unavailable : TutorialNetworkState
}