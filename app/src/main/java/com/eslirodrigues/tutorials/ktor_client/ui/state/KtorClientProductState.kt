package com.eslirodrigues.tutorials.ktor_client.ui.state

import com.eslirodrigues.tutorials.ktor_client.data.local.model.KtorClientProduct

data class KtorClientProductState(
    val product: KtorClientProduct? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
