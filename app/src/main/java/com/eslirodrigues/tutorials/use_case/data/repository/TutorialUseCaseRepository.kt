package com.eslirodrigues.tutorials.use_case.data.repository

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TutorialUseCaseRepository @Inject constructor() {
    fun getList() = flow { emit(listOf("Catfish", "Carp", "Clownfish")) }
}