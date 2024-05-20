package com.develo.vectorket.core.domain.use_case

import com.develo.vectorket.core.domain.repository.DataStoreRepository

class GetPrefsUseCase(
    private val repository: DataStoreRepository
) {
    operator fun invoke() = repository.getPrefs()
}