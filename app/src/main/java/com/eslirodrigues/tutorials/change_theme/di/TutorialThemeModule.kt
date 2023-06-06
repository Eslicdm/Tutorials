package com.eslirodrigues.tutorials.change_theme.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.eslirodrigues.tutorials.change_theme.datastore.TutorialThemeDataStoreConstants.THEME_DATA_STORE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TutorialThemeModule {

    @Singleton
    @Provides
    @Named(THEME_DATA_STORE)
    fun provideThemeDataStore(
        @ApplicationContext context: Context
    ) : DataStore<Preferences> = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(THEME_DATA_STORE)
    }
}