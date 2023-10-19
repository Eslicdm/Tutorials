package com.eslirodrigues.tutorials.state_stateflow_viewmodel.data

sealed class TutorialStateResult<out R> {
    data class Success<out T>(val data: T) : TutorialStateResult<T>()
    data class Error(val exception: Throwable) : TutorialStateResult<Nothing>()
    data object Loading : TutorialStateResult<Nothing>()
}