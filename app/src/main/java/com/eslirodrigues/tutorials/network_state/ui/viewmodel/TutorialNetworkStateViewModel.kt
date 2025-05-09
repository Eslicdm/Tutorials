package com.eslirodrigues.tutorials.network_state.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.network_state.network.TutorialNetworkManager
import com.eslirodrigues.tutorials.network_state.network.TutorialNetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TutorialNetworkStateViewModel @Inject constructor(
    networkManager: TutorialNetworkManager
) : ViewModel() {

    val networkState: StateFlow<TutorialNetworkState> = networkManager.getNetworkState().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = TutorialNetworkState.Lost
    )
}