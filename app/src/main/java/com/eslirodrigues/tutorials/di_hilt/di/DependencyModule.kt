package com.eslirodrigues.tutorials.di_hilt.di

import android.content.Context
import com.eslirodrigues.tutorials.di_hilt.data.api.DependencyApi
import com.eslirodrigues.tutorials.di_hilt.data.repository.DependencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // ActivityComponent, ViewModelComponent
object DependencyModule {

    @Singleton
    @Provides
    fun provideDependencyApi(
        @ApplicationContext context: Context
    ) : DependencyApi = DependencyApi(context = context)

    @Singleton
    @Provides
    fun provideRepository(
        dependencyApi: DependencyApi,
        @Named("StringOne")
        stringOne: String,
        @Named("StringTwo")
        stringTwo: String
    ): DependencyRepository = DependencyRepository(
        dependencyApi = dependencyApi,
        stringOne = stringOne,
        stringTwo = stringTwo
    )

    @Singleton
    @Provides
    @Named("StringOne")
    fun provideStringOne(): String = "StringOne"

    @Singleton
    @Provides
    @Named("StringTwo")
    fun provideStringTwo(): String = "StringTwo"
}