package com.eslirodrigues.tutorials.data_store.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TutorialDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object DataStoreKeys {
        const val NAME_KEY = "NAME_KEY"
        val nameKey = stringPreferencesKey(NAME_KEY)
    }

    suspend fun saveName(name: String) { dataStore.edit { it[DataStoreKeys.nameKey] = name } }

    val readName: Flow<String> = dataStore.data.map { it[DataStoreKeys.nameKey] ?: "" }
}