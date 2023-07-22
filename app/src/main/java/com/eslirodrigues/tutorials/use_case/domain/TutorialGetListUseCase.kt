package com.eslirodrigues.tutorials.use_case.domain

import com.eslirodrigues.tutorials.use_case.data.repository.TutorialUseCaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TutorialGetListUseCase @Inject constructor(
    private val tutorialUseCaseRepository: TutorialUseCaseRepository
) {
    operator fun invoke(): Flow<List<String>> = flow {
        try {
            emit(tutorialUseCaseRepository.getList().first())
        } catch (e: Exception) {
            emit(listOf("error"))
        }
    }

    suspend fun getList(): Flow<List<String>> = flow {
        try {
            emit(tutorialUseCaseRepository.getList().first())
        } catch (e: Exception) {
            emit(listOf("error"))
        }
    }
}