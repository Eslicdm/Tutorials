package com.eslirodrigues.tutorials.use_case.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.use_case.domain.TutorialGetListUseCase
import com.eslirodrigues.tutorials.use_case.ui.state.TutorialUseCaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorialUseCaseViewModel @Inject constructor(
    private val tutorialGetListUseCase: TutorialGetListUseCase
) : ViewModel() {

    private val _textState = MutableStateFlow(TutorialUseCaseState())
    val textState: StateFlow<TutorialUseCaseState> = _textState.asStateFlow()

    init { getList() }

    private fun getList() = viewModelScope.launch {
        tutorialGetListUseCase.getList().collectLatest { list ->
            _textState.update { it.copy(data = list, isLoading = false, errorMsg = null) }
        }
    }

    val textStateIn: StateFlow<List<String>> = tutorialGetListUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = listOf("Initial Value")
    )
}