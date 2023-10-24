package com.eslirodrigues.tutorials.window_manager.repository

import javax.inject.Inject

class TutorialWindowManagerRepository @Inject constructor() {
    private val textList = listOf(
        "Text 1" to "Description 1",
        "Text 2" to "Description 2",
        "Text 3" to "Description 3"
    )
    fun getTextList() = textList
}