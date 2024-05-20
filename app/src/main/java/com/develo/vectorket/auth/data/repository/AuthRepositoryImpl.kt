package com.develo.vectorket.auth.data.repository

import com.develo.vectorket.auth.domain.alias.GoogleSignInResponse
import com.develo.vectorket.auth.domain.mapper.toUser
import com.develo.vectorket.auth.domain.repository.AuthRepository
import com.develo.vectorket.core.domain.model.Response.Failure
import com.develo.vectorket.core.domain.model.Response.Success
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override fun getUser(scope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser?.toUser())
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(scope, SharingStarted.WhileSubscribed(), auth.currentUser?.toUser())

    override suspend fun signInWithGoogle(googleCredential: AuthCredential): GoogleSignInResponse {
        return try {
            auth.signInWithCredential(googleCredential).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}