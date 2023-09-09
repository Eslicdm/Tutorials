package com.eslirodrigues.tutorials.ktor_client.di

import android.content.Context
import com.eslirodrigues.tutorials.BuildConfig
import com.eslirodrigues.tutorials.ktor_client.data.local.repository.KtorClientRepository
import com.eslirodrigues.tutorials.ktor_client.data.local.repository.KtorClientRepositoryImpl
import com.eslirodrigues.tutorials.ktor_client.data.remote.service.TutorialKtorClientItemService
import com.eslirodrigues.tutorials.ktor_client.data.remote.service.TutorialKtorClientProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TutorialKtorClientModule {
    @Singleton
    @Provides
    @Named("item")
    fun provideKtorClientItem(): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }

    @Singleton
    @Provides
    @Named("product")
    fun provideKtorHttpClientProduct(): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    coerceInputValues = true
                }
            )
        }
        defaultRequest {
            header("X-RapidAPI-Key", BuildConfig.API_KEY)
            header("X-RapidAPI-Host", "onboarding-project5973.p.rapidapi.com")
            url("https://onboarding-project5973.p.rapidapi.com/")
        }
    }

    @Singleton
    @Provides
    fun provideKtorClientRepository(
        ktorClientItemService: TutorialKtorClientItemService,
        ktorClientProductService: TutorialKtorClientProductService,
        @ApplicationContext context: Context
    ): KtorClientRepository = KtorClientRepositoryImpl(
        ktorClientItemService = ktorClientItemService,
        ktorClientProductService = ktorClientProductService,
        context = context
    )
}