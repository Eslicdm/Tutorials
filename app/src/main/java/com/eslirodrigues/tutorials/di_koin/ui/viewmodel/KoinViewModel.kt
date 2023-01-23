package com.eslirodrigues.tutorials.di_koin.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.eslirodrigues.tutorials.di_koin.data.repository.KoinRepository

class KoinViewModel(
    repository: KoinRepository
) : ViewModel() {
    val dependencyString = repository.getString()
    val stringOne = repository.getStringOne()
    val stringTwo = repository.getStringTwo()
}