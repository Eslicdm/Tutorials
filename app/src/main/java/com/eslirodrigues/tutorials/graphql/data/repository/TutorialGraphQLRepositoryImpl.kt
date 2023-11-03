package com.eslirodrigues.tutorials.graphql.data.repository

import com.eslirodrigues.tutorials.graphql.data.TutorialGraphQLResult
import com.eslirodrigues.tutorials.graphql.data.model.PokeSpecie
import com.eslirodrigues.tutorials.graphql.data.remote.TutorialGraphQLClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TutorialGraphQLRepositoryImpl @Inject constructor(
    private val graphQLClient: TutorialGraphQLClient
) : TutorialGraphQLRepository {
    override fun getPokeSpecies(): Flow<TutorialGraphQLResult<List<PokeSpecie>>> =
        graphQLClient.getPokeSpecies()
}