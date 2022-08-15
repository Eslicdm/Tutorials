package com.eslirodrigues.tutorials.swipetorefresh_accompanist.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SwipeViewModel : ViewModel() {

    var list = mutableStateListOf(1)
        private set

    fun getSwipeList() = viewModelScope.launch {
        repeat(3) {
            list.add(it)
        }
    }
}