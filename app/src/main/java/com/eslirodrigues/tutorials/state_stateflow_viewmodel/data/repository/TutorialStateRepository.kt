package com.eslirodrigues.tutorials.state_stateflow_viewmodel.data.repository

import com.eslirodrigues.tutorials.state_stateflow_viewmodel.data.TutorialStateResult
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.data.model.TutorialStateUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TutorialStateRepository @Inject constructor() {
    private val userList = listOf(
        TutorialStateUser("FKaren"),
        TutorialStateUser("FJosh"),
        TutorialStateUser("FMichael"),
        TutorialStateUser("FBatman"),
    )

    fun getUsers(): Flow<TutorialStateResult<List<TutorialStateUser>>> = flow {
        delay(2000)
        emit(TutorialStateResult.Success(userList))
        delay(1000)

        emit(TutorialStateResult.Loading)
        delay(1000)

        emit(TutorialStateResult.Error(Throwable("Error")))
        delay(1000)

        emit(TutorialStateResult.Loading)
        delay(1000)

        emit(TutorialStateResult.Success(emptyList()))

        delay(2000)
        emit(TutorialStateResult.Success(userList))
    }
}