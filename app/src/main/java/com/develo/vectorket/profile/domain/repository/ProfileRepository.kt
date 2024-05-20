package com.develo.vectorket.profile.domain.repository

interface ProfileRepository {
    suspend fun signOut()
}