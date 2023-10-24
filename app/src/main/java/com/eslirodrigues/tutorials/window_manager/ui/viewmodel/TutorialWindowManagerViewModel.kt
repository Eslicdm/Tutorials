package com.eslirodrigues.tutorials.window_manager.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.eslirodrigues.tutorials.window_manager.repository.TutorialWindowManagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialWindowManagerViewModel @Inject constructor(
    private val windowManagerRepository: TutorialWindowManagerRepository
) : ViewModel() {
    fun getTextList() = windowManagerRepository.getTextList()
}
