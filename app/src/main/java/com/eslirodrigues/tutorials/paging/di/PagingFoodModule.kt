package com.eslirodrigues.tutorials.paging.di

import com.eslirodrigues.tutorials.paging.data.api.PagingFoodApi
import com.eslirodrigues.tutorials.paging.data.repository.PagingFoodRepository
import com.eslirodrigues.tutorials.paging.data.repository.PagingFoodRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingFoodModule {

    @Singleton
    @Provides
    fun providePagingRetrofitApi(moshi: Moshi): PagingFoodApi = Retrofit.Builder()
        .baseUrl("https://foodish-api.herokuapp.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(PagingFoodApi::class.java)

    @Singleton
    @Provides
    fun providePagingFoodRepository(
        foodApi: PagingFoodApi
    ): PagingFoodRepository = PagingFoodRepositoryImpl(foodApi)
}