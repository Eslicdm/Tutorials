package com.eslirodrigues.tutorials.admob.di

import android.content.Context
import com.eslirodrigues.tutorials.admob.ui.components.AdMobInterstitial
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdMobModule {

    @Singleton
    @Provides
    fun provideAdMobInterstitial(
        @ApplicationContext context: Context
    ): AdMobInterstitial = AdMobInterstitial(context)
}