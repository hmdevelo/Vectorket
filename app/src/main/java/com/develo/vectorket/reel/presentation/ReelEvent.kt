package com.develo.vectorket.reel.presentation

import com.develo.vectorket.core.domain.model.Vector

sealed class ReelEvent {
    data class AddToFavorites(val vector: Vector) : ReelEvent()
    data class RemoveFromFavorites(val vector: Vector) : ReelEvent()
    data class Download(val vector: Vector) : ReelEvent()
}