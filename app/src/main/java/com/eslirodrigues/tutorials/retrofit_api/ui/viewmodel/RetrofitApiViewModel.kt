package com.eslirodrigues.tutorials.retrofit_api.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.retrofit_api.data.RetrofitApiResult
import com.eslirodrigues.tutorials.retrofit_api.data.model.dto.RetrofitApiProductPost
import com.eslirodrigues.tutorials.retrofit_api.data.repository.RetrofitApiRepository
import com.eslirodrigues.tutorials.retrofit_api.ui.state.RetrofitApiItemState
import com.eslirodrigues.tutorials.retrofit_api.ui.state.RetrofitApiProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitApiViewModel @Inject constructor(
    private val repository: RetrofitApiRepository
) : ViewModel() {

    private val _itemState = MutableStateFlow(RetrofitApiItemState())
    val itemState: StateFlow<RetrofitApiItemState> = _itemState.asStateFlow()

    private val _productState = MutableStateFlow(RetrofitApiProductState())
    val productState: StateFlow<RetrofitApiProductState> = _productState.asStateFlow()

    fun getItemFromApi() = viewModelScope.launch {
        repository.getItemsFromApi().collect { result ->
            when (result) {
                is RetrofitApiResult.Success -> {
                    _itemState.update {
                        it.copy(item = result.data, isLoading = false, errorMsg = null)
                    }
                }
                is RetrofitApiResult.Error -> {
                    _itemState.update {
                        it.copy(item = null, isLoading = false, errorMsg = result.exception.message)
                    }
                }
                is RetrofitApiResult.Loading -> {
                    _itemState.update {
                        it.copy(item = null, isLoading = true, errorMsg = null)
                    }
                }
            }
        }
    }

    fun createProduct(productPost: RetrofitApiProductPost) = viewModelScope.launch {
        repository.createProduct(productPost).collect { result ->
            when (result) {
                is RetrofitApiResult.Success -> {
                    _productState.update {
                        it.copy(product = result.data, isLoading = false, errorMsg = null)
                    }
                }
                is RetrofitApiResult.Error -> {
                    _productState.update {
                        it.copy(product = null, isLoading = false, errorMsg = result.exception.message)
                    }
                }
                is RetrofitApiResult.Loading -> {
                    _productState.update {
                        it.copy(product = null, isLoading = true, errorMsg = null)
                    }
                }
            }
        }
    }

    fun deleteProductById(id: String) = viewModelScope.launch {
        repository.deleteProductById(id)
    }
}