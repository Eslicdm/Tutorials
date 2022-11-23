package com.eslirodrigues.tutorials.retrofit_api.ui.state

import com.eslirodrigues.tutorials.retrofit_api.data.model.RetrofitApiItem

data class RetrofitApiItemState(
    val item: RetrofitApiItem? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
