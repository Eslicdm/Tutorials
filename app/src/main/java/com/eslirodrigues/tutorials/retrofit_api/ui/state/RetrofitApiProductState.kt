package com.eslirodrigues.tutorials.retrofit_api.ui.state

import com.eslirodrigues.tutorials.retrofit_api.data.model.RetrofitApiProduct

data class RetrofitApiProductState(
    val product: RetrofitApiProduct? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
