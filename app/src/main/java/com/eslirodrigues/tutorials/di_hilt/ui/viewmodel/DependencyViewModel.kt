package com.eslirodrigues.tutorials.di_hilt.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.eslirodrigues.tutorials.di_hilt.data.repository.DependencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DependencyViewModel @Inject constructor(
    repository: DependencyRepository
) : ViewModel() {
    val dependencyString = repository.getString()
    val stringOne = repository.getStringOne()
    val stringTwo = repository.getStringTwo()
}