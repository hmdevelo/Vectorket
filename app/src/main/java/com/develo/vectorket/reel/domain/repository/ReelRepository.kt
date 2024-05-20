package com.develo.vectorket.reel.domain.repository

import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.reel.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface ReelRepository {

    fun getFavorites(userId: String): Flow<List<Favorite>>

    suspend fun addToFavorites(fav: Favorite)

    suspend fun removeFromFavorites(fav: Favorite)

    suspend fun updateDownload(vector: Vector, increment: Long)
}