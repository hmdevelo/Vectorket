package com.develo.vectorket.home.domain.use_case

import com.develo.vectorket.home.domain.repository.HomeRepository

class GetLatestVectorUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke() = repository.getLatestVector()
}