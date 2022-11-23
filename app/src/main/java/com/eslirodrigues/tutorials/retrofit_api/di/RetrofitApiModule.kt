package com.eslirodrigues.tutorials.retrofit_api.di

import android.content.Context
import com.eslirodrigues.tutorials.BuildConfig
import com.eslirodrigues.tutorials.retrofit_api.data.api.RetrofitApiItemService
import com.eslirodrigues.tutorials.retrofit_api.data.api.RetrofitApiProductService
import com.eslirodrigues.tutorials.retrofit_api.data.repository.RetrofitApiRepository
import com.eslirodrigues.tutorials.retrofit_api.data.repository.RetrofitApiRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitApiModule {

    @Singleton
    @Provides
    fun provideOkHTTP(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor { chain ->
            chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
                .addHeader("X-RapidAPI-Host", "onboarding-project5973.p.rapidapi.com")
                .build()
                .let(chain::proceed)
        }
        .build()


    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofitProduct(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
    ) : RetrofitApiProductService = Retrofit.Builder()
        .baseUrl("https://onboarding-project5973.p.rapidapi.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create(RetrofitApiProductService::class.java)

    @Singleton
    @Provides
    fun provideRetrofitItem(
        moshi: Moshi,
    ) : RetrofitApiItemService = Retrofit.Builder()
        .baseUrl("https://tasty.p.rapidapi.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(RetrofitApiItemService::class.java)

    @Singleton
    @Provides
    fun provideRepository(
        retrofitApiItem: RetrofitApiItemService,
        retrofitApiProduct: RetrofitApiProductService,
        @ApplicationContext context: Context
    ): RetrofitApiRepository = RetrofitApiRepositoryImpl(
        retrofitApiItem = retrofitApiItem,
        retrofitApiProduct = retrofitApiProduct,
        context = context
    )
}