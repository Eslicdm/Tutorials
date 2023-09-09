package com.eslirodrigues.tutorials.ktor_client.ui.state

import com.eslirodrigues.tutorials.ktor_client.data.local.model.KtorClientItem

data class KtorClientItemState(
    val item: KtorClientItem? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
