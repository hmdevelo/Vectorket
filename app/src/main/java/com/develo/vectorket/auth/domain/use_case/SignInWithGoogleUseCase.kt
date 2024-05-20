package com.develo.vectorket.auth.domain.use_case

import com.develo.vectorket.auth.domain.repository.AuthRepository
import com.google.firebase.auth.AuthCredential

class SignInWithGoogleUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(googleCredential: AuthCredential) =
        repository.signInWithGoogle(googleCredential)
}