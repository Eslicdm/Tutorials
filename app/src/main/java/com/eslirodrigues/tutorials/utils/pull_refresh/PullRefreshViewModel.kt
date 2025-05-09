package com.eslirodrigues.tutorials.utils.pull_refresh

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PullRefreshViewModel : ViewModel() {

    var list = mutableStateListOf(1)
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    fun getSwipeList() = viewModelScope.launch {
        isRefreshing = true
        delay(2000L)
        repeat(3) {
            list.add(it)
            isRefreshing = false
        }
    }
}