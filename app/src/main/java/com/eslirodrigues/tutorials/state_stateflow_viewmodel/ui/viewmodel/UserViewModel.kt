package com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.data.TutorialStateResult
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.data.model.TutorialStateUser
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.data.repository.TutorialStateRepository
import com.eslirodrigues.tutorials.state_stateflow_viewmodel.ui.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    repository: TutorialStateRepository,
) : ViewModel() {

//    val userState: StateFlow<UserState> = repository.getUsers().map { result ->
//        when (result) {
//            is TutorialStateResult.Success -> UserState(data = result.data)
//            is TutorialStateResult.Error -> UserState(errorMsg = result.exception.message)
//            is TutorialStateResult.Loading -> UserState(isLoading = true)
//        }
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = UserState(isLoading = true)
//    )

    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> =
        combine(_userState, repository.getUsers()) { local, repository ->
            when (repository) {
                is TutorialStateResult.Success -> {
                    if (local.data != null) {
                        val list = listOf(repository.data, local.data).flatten()
                        UserState(data = list)
                    } else {
                        UserState(data = repository.data)
                    }
                }
                is TutorialStateResult.Error -> UserState(errorMsg = repository.exception.message)
                is TutorialStateResult.Loading -> UserState(isLoading = true)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserState(isLoading = true)
        )

    // Criar um evento
    fun addUserList(nameList: List<TutorialStateUser>) = viewModelScope.launch {
        _userState.update { it.copy(data = nameList) }
    }
}