package com.develo.vectorket.feed.di

import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.feed.data.repository.FeedRepositoryImpl
import com.develo.vectorket.feed.domain.repository.FeedRepository
import com.develo.vectorket.feed.domain.use_case.GetLatestVectorsQueryUseCase
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedModule {

    @Provides
    @Singleton
    fun provideFeedRepository(
        @Named(VECTORS_COLLECTION)
        vectors: CollectionReference
    ): FeedRepository = FeedRepositoryImpl(vectors)

    @Provides
    @Singleton
    fun provideGetLatestVectorsQueryUseCase(
        repository: FeedRepository
    ) = GetLatestVectorsQueryUseCase(repository)
}