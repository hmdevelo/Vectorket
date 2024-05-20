package com.develo.vectorket.auth.domain.repository

import com.develo.vectorket.auth.domain.alias.GoogleSignInResponse
import com.develo.vectorket.auth.domain.model.User
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {

    fun getUser(scope: CoroutineScope): StateFlow<User?>

    suspend fun signInWithGoogle(googleCredential: AuthCredential): GoogleSignInResponse
}