package com.develo.vectorket.reel.domain.use_case

import com.develo.vectorket.reel.domain.model.Favorite
import com.develo.vectorket.reel.domain.repository.ReelRepository

class RemoveFromFavoritesUseCase(
    private val repository: ReelRepository
) {
    suspend operator fun invoke(fav: Favorite) {
        repository.removeFromFavorites(fav)
    }
}