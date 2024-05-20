package com.develo.vectorket.core.domain.repository

import androidx.datastore.preferences.core.stringPreferencesKey
import com.develo.vectorket.core.domain.model.Prefs
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    companion object {
        const val DATA_STORE_KEY = "vectorket_prefs"
        val SEARCH_HISTORIES_KEY = stringPreferencesKey("search_histories")
    }

    fun getPrefs(): Flow<Prefs>

    suspend fun savePrefs(prefs: Prefs)
}