package com.eslirodrigues.tutorials.graphql.data.repository

import com.eslirodrigues.tutorials.graphql.data.TutorialGraphQLResult
import com.eslirodrigues.tutorials.graphql.data.model.PokeSpecie
import kotlinx.coroutines.flow.Flow

interface TutorialGraphQLRepository {
    fun getPokeSpecies() : Flow<TutorialGraphQLResult<List<PokeSpecie>>>
}