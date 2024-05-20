package com.develo.vectorket.core.domain.use_case

import com.develo.vectorket.core.domain.model.Prefs
import com.develo.vectorket.core.domain.repository.DataStoreRepository

class SavePrefsUseCase(
    private val repository: DataStoreRepository
) {
    suspend operator fun invoke(prefs: Prefs) {
        repository.savePrefs(prefs)
    }
}