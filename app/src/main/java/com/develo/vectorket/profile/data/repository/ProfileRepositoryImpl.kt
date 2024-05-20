package com.develo.vectorket.profile.data.repository

import com.develo.vectorket.profile.domain.repository.ProfileRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val signInClient: GoogleSignInClient
) : ProfileRepository {

    override suspend fun signOut() {
        signInClient.signOut().await()
        auth.signOut()
    }
}