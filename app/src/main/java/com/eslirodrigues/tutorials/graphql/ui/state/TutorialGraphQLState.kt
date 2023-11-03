package com.eslirodrigues.tutorials.graphql.ui.state

import com.eslirodrigues.tutorials.graphql.data.model.PokeSpecie


data class TutorialGraphQLState(
    val data: List<PokeSpecie>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)