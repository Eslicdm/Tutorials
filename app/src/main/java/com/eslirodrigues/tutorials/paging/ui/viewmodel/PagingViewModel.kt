package com.eslirodrigues.tutorials.paging.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.eslirodrigues.tutorials.paging.data.repository.PagingFoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val repository: PagingFoodRepository
) : ViewModel() {
    fun getAllPizzas() = repository.getAllPizzas().cachedIn(viewModelScope)
}