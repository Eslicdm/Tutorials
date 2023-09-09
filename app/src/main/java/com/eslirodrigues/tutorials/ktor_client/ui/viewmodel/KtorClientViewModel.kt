package com.eslirodrigues.tutorials.ktor_client.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.ktor_client.data.TutorialKtorClientResult
import com.eslirodrigues.tutorials.ktor_client.data.local.repository.KtorClientRepository
import com.eslirodrigues.tutorials.ktor_client.data.remote.dto.KtorClientProductPost
import com.eslirodrigues.tutorials.ktor_client.ui.state.KtorClientItemState
import com.eslirodrigues.tutorials.ktor_client.ui.state.KtorClientProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KtorClientViewModel @Inject constructor(
    private val repository: KtorClientRepository
) : ViewModel() {

    private val _itemState = MutableStateFlow(KtorClientItemState())
    val itemState: StateFlow<KtorClientItemState> = _itemState.asStateFlow()

    private val _productState = MutableStateFlow(KtorClientProductState())
    val productState: StateFlow<KtorClientProductState> = _productState.asStateFlow()

    fun getItemFromApi() = viewModelScope.launch {
        repository.getItemsFromApi().collect { result ->
            when(result) {
                is TutorialKtorClientResult.Success -> {
                    _itemState.update {
                        it.copy(item = result.data, isLoading = false, errorMsg = null)
                    }
                }
                is TutorialKtorClientResult.Error -> {
                    _itemState.update {
                        it.copy(item = null, isLoading = false, errorMsg = result.exception.message)
                    }
                }
                is TutorialKtorClientResult.Loading -> {
                    _itemState.update { it.copy(item = null, isLoading = true, errorMsg = null) }
                }
            }
        }
    }

    fun createProduct(productPost: KtorClientProductPost) = viewModelScope.launch {
        repository.createProduct(productPost).collect { result ->
            when (result) {
                is TutorialKtorClientResult.Success -> {
                    _productState.update {
                        it.copy(product = result.data, isLoading = false, errorMsg = null)
                    }
                }
                is TutorialKtorClientResult.Error -> {
                    _productState.update {
                        it.copy(product = null, isLoading = false, errorMsg = result.exception.message)
                    }
                }
                is TutorialKtorClientResult.Loading -> {
                    _productState.update { it.copy(product = null, isLoading = true, errorMsg = null) }
                }
            }
        }
    }

    fun deleteProductById(id: String) = viewModelScope.launch { repository.deleteProductById(id) }
}