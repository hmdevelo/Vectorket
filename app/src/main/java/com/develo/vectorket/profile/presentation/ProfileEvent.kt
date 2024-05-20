package com.develo.vectorket.profile.presentation

import com.develo.vectorket.reel.domain.model.Favorite

sealed class ProfileEvent {
    data object LogOut : ProfileEvent()
    data class ResetFavorites(val favorites: List<Favorite>) : ProfileEvent()
}