package com.eslirodrigues.tutorials.graphql.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.graphql.data.TutorialGraphQLResult
import com.eslirodrigues.tutorials.graphql.data.repository.TutorialGraphQLRepository
import com.eslirodrigues.tutorials.graphql.ui.state.TutorialGraphQLState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TutorialGraphQLViewModel @Inject constructor(
    repository: TutorialGraphQLRepository
) : ViewModel() {
    val pokeState: StateFlow<TutorialGraphQLState> = repository.getPokeSpecies().map { result ->
        when (result) {
            is TutorialGraphQLResult.Success -> TutorialGraphQLState(data = result.data)
            is TutorialGraphQLResult.Error -> TutorialGraphQLState(errorMsg = result.exception.message)
            is TutorialGraphQLResult.Loading -> TutorialGraphQLState(isLoading = true)
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = TutorialGraphQLState(isLoading = true),
        started = SharingStarted.WhileSubscribed(5000)
    )
}

