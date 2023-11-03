package com.eslirodrigues.tutorials.graphql.di

import com.apollographql.apollo3.ApolloClient
import com.eslirodrigues.tutorials.graphql.data.remote.TutorialGraphQLClient
import com.eslirodrigues.tutorials.graphql.data.repository.TutorialGraphQLRepository
import com.eslirodrigues.tutorials.graphql.data.repository.TutorialGraphQLRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TutorialGraphQLModule {

    @Singleton
    @Provides
    fun provideApolloClient() = ApolloClient.Builder()
        .serverUrl("https://beta.pokeapi.co/graphql/v1beta")
        .build()

    @Singleton
    @Provides
    fun provideGraphQLRepository(
        graphQLClient: TutorialGraphQLClient
    ) : TutorialGraphQLRepository = TutorialGraphQLRepositoryImpl(graphQLClient)
}