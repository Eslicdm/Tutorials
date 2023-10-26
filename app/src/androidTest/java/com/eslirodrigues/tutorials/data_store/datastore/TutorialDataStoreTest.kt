package com.eslirodrigues.tutorials.data_store.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TutorialDataStoreTest {

    private val testContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
            produceFile = { testContext.preferencesDataStoreFile("test_datastore") }
        )

    private val dataStoreClass = TutorialDataStore(dataStore)

    @Test
    fun setName_Assert_IsNameRight() = runTest {
        dataStoreClass.saveName("car")

        val name = dataStoreClass.readName.first()

        assertThat(name).isEqualTo("car")
    }
}