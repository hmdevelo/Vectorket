package com.develo.vectorket.feed.domain.use_case

import com.develo.vectorket.feed.domain.repository.FeedRepository

class GetLatestVectorsQueryUseCase(
    private val repository: FeedRepository
) {
    operator fun invoke(limit: Long) = repository.getLatestVectorsQuery(limit)
}