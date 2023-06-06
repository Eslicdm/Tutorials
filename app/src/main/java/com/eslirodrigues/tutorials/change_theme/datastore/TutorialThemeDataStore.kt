package com.eslirodrigues.tutorials.change_theme.datastore

import android.content.res.Configuration
import android.content.res.Resources
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.eslirodrigues.tutorials.change_theme.datastore.TutorialThemeDataStoreConstants.THEME_DATA_STORE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class TutorialThemeDataStore @Inject constructor(
    @Named(THEME_DATA_STORE) private val themeDataStore: DataStore<Preferences>
) {
    private object DataStoreKeys {
        const val THEME_KEY = "THEME_KEY"
        val themeKey = booleanPreferencesKey(THEME_KEY)
    }

    private val systemTheme =
        when (Resources.getSystem().configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> { true }
            Configuration.UI_MODE_NIGHT_NO -> { false }
            else -> { false }
        }

    suspend fun enableDarkTheme(enabled: Boolean) {
        themeDataStore.edit { it[DataStoreKeys.themeKey] = enabled }
    }

    val isDarkThemeEnabled: Flow<Boolean> = themeDataStore.data.map {
        it[DataStoreKeys.themeKey] ?: systemTheme
    }
}