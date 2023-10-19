package com.eslirodrigues.tutorials.savedstatehandle.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
class TutorialSavedStateHandleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var savedOption: String by savedStateHandle.saveable { mutableStateOf("") }
        private set

    fun saveOption(option: String) = viewModelScope.launch {
        savedOption = option
    }
}