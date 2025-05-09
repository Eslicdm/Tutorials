package com.eslirodrigues.tutorials.utils.searchbar_state_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchBarViewModel : ViewModel() {

    private val _dogState = MutableStateFlow(DogState())
    val dogState: StateFlow<DogState> = _dogState.asStateFlow()

    private var initialList = listOf<String>()

    init {
        val dogList = listOf("Bulldog", "Foxhound", "Terrier", "Leopard", "Shepherd", "Beagle")
        initialList = dogList
        _dogState.update { it.copy(data = dogList, isLoading = false) }
    }

    fun searchDog(text: String) = viewModelScope.launch {
        if (text.isEmpty()) {
            _dogState.update { it.copy(data = initialList, isLoading = false) }
        } else {
            val searchList = initialList.filter { it.contains(text.trim(), ignoreCase = true) }
            _dogState.update { it.copy(data = searchList, isLoading = false) }
        }
    }
}