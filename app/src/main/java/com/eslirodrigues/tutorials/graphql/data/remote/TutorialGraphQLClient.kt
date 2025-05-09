package com.eslirodrigues.tutorials.graphql.data.remote

import com.apollographql.apollo.ApolloClient
import com.eslirodrigues.tutorials.SamplePokeAPIqueryQuery
import com.eslirodrigues.tutorials.graphql.data.TutorialGraphQLResult
import com.eslirodrigues.tutorials.graphql.data.model.PokeSpecie
import com.eslirodrigues.tutorials.graphql.data.model.toPokeSpecies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TutorialGraphQLClient @Inject constructor(
    private val apolloClient: ApolloClient
) {
    // app/build/generated/source/apollo - query location
    fun getPokeSpecies(): Flow<TutorialGraphQLResult<List<PokeSpecie>>> = flow {
        emit(TutorialGraphQLResult.Loading)
        try {
            val response = apolloClient.query(SamplePokeAPIqueryQuery()).executeV3()
            val list = response.data?.gen3_species?.map { it.toPokeSpecies() }.orEmpty()
            emit(TutorialGraphQLResult.Success(list))
        } catch (e: Exception) { emit(TutorialGraphQLResult.Error(e)) }
    }.flowOn(Dispatchers.IO)
}