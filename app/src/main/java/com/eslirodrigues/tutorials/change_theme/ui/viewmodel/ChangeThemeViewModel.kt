package com.eslirodrigues.tutorials.change_theme.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.change_theme.datastore.TutorialThemeDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeThemeViewModel @Inject constructor(
    private val themeDataStore: TutorialThemeDataStore
) : ViewModel() {

    val isDarkThemeEnabled: StateFlow<Boolean> = themeDataStore.isDarkThemeEnabled.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = false
    )

    fun enableDarkTheme(enabled: Boolean) = viewModelScope.launch {
        themeDataStore.enableDarkTheme(enabled)
    }
}