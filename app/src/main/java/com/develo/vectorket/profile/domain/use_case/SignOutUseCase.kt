package com.develo.vectorket.profile.domain.use_case

import com.develo.vectorket.profile.domain.repository.ProfileRepository

class SignOutUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() {
        repository.signOut()
    }
}