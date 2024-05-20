package com.develo.vectorket.reel.domain.use_case

import com.develo.vectorket.reel.domain.repository.ReelRepository

class GetFavoritesUseCase(
    private val repository: ReelRepository
) {
    operator fun invoke(userId: String) = repository.getFavorites(userId)
}