package com.develo.vectorket.home.di

import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.home.data.repository.HomeRepositoryImpl
import com.develo.vectorket.home.domain.repository.HomeRepository
import com.develo.vectorket.home.domain.use_case.GetLatestVectorUseCase
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        @Named(VECTORS_COLLECTION)
        vectors: CollectionReference
    ): HomeRepository = HomeRepositoryImpl(vectors)

    @Provides
    @Singleton
    fun provideGetLatestUseCase(
        repository: HomeRepository
    ) = GetLatestVectorUseCase(repository)
}