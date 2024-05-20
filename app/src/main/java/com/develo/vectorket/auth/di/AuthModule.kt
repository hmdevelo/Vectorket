package com.develo.vectorket.auth.di

import android.app.Application
import com.develo.vectorket.R
import com.develo.vectorket.auth.data.repository.AuthRepositoryImpl
import com.develo.vectorket.auth.domain.repository.AuthRepository
import com.develo.vectorket.auth.domain.use_case.GetUserUseCase
import com.develo.vectorket.auth.domain.use_case.SignInWithGoogleUseCase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideGoogleSignInOptions(
        app: Application
    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        app: Application,
        options: GoogleSignInOptions
    ) = GoogleSignIn.getClient(app, options)

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository = AuthRepositoryImpl(auth)

    @Provides
    @Singleton
    fun provideSignInWithGoogleUseCase(
        repository: AuthRepository
    ) = SignInWithGoogleUseCase(repository)

    @Provides
    @Singleton
    fun provideGetUserUseCase(
        repository: AuthRepository
    ) = GetUserUseCase(repository)
}