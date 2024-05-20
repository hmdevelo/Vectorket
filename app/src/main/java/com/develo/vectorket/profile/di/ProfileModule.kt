package com.develo.vectorket.profile.di

import com.develo.vectorket.core.data.query.QueryBuilder
import com.develo.vectorket.profile.data.repository.ProfileRepositoryImpl
import com.develo.vectorket.profile.domain.repository.ProfileRepository
import com.develo.vectorket.profile.domain.use_case.GetFavoritePagingSourceUseCase
import com.develo.vectorket.profile.domain.use_case.SignOutUseCase
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideGetFavoritePagingSourceUseCase(
        queryBuilder: QueryBuilder
    ) = GetFavoritePagingSourceUseCase(queryBuilder)

    @Provides
    @Singleton
    fun provideProfileRepository(
        auth: FirebaseAuth,
        signInClient: GoogleSignInClient
    ): ProfileRepository = ProfileRepositoryImpl(auth, signInClient)

    @Provides
    @Singleton
    fun provideSignOutUseCase(
        repository: ProfileRepository
    ) = SignOutUseCase(repository)
}