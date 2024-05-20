package com.develo.vectorket.search.domain.use_case

import com.develo.vectorket.search.domain.repository.SearchRepository

class BuildQueryUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(criteria: String) = repository.buildQuery(criteria)
}