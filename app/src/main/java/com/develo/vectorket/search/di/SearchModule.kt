package com.develo.vectorket.search.di

import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.core.domain.service.RandomService
import com.develo.vectorket.search.data.repository.SearchRepositoryImpl
import com.develo.vectorket.search.domain.repository.SearchRepository
import com.develo.vectorket.search.domain.use_case.BuildQueryUseCase
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchRepository(
        @Named(VECTORS_COLLECTION)
        vectors: CollectionReference,
        randomService: RandomService
    ): SearchRepository = SearchRepositoryImpl(vectors, randomService)

    @Provides
    @Singleton
    fun provideBuildQueryUseCase(
        repository: SearchRepository
    ) = BuildQueryUseCase(repository)
}