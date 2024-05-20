package com.develo.vectorket.reel.domain.mapper

import com.develo.vectorket.core.domain.model.Vector
import com.develo.vectorket.reel.domain.model.Favorite

fun Vector.toFavorite(userId: String) = Favorite(
    userId = userId,
    vectorId = uid
)