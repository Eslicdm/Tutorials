package com.eslirodrigues.tutorials.use_case.domain

import com.eslirodrigues.tutorials.use_case.data.repository.TutorialUseCaseRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TutorialGetListUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    private val dispatcher = StandardTestDispatcher()
    private lateinit var tutorialGetListUseCase: TutorialGetListUseCase
    @MockK lateinit var tutorialUseCaseRepository: TutorialUseCaseRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        tutorialGetListUseCase = TutorialGetListUseCase(tutorialUseCaseRepository)

        coEvery {
            tutorialUseCaseRepository.getList()
        } returns flow { emit(listOf("Catfish", "Carp", "Clownfish")) }
    }

    @After
    fun close() { Dispatchers.resetMain() }

    @Test
    fun getList_Assert_ListItemsAreRight() = runTest {
        val valueInvoke = tutorialGetListUseCase().last()
        val valueSuspend = tutorialGetListUseCase.getList().last()

        dispatcher.scheduler.advanceUntilIdle()

        Truth.assertThat(valueInvoke).isEqualTo(listOf("Catfish", "Carp", "Clownfish"))
        Truth.assertThat(valueSuspend).isEqualTo(listOf("Catfish", "Carp", "Clownfish"))
    }
}