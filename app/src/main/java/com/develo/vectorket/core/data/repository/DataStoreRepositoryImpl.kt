package com.develo.vectorket.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.develo.vectorket.core.domain.model.Prefs
import com.develo.vectorket.core.domain.repository.DataStoreRepository
import com.develo.vectorket.core.domain.repository.DataStoreRepository.Companion.SEARCH_HISTORIES_KEY
import com.develo.vectorket.core.domain.util.toList
import com.develo.vectorket.core.domain.util.toSplittableString
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {

    override fun getPrefs() = dataStore.data.map { preferences ->
        Prefs(
            searchHistories = preferences[SEARCH_HISTORIES_KEY].toList()
        )
    }

    override suspend fun savePrefs(prefs: Prefs) {
        dataStore.edit { preferences ->
            preferences[SEARCH_HISTORIES_KEY] = prefs.searchHistories.toSplittableString()
        }
    }
}