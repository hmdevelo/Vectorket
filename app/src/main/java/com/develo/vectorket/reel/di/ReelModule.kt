package com.develo.vectorket.reel.di

import android.content.Context
import com.develo.vectorket.core.data.util.FAVORITES_COLLECTION
import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.reel.data.repository.ReelRepositoryImpl
import com.develo.vectorket.reel.data.service.DownloaderImpl
import com.develo.vectorket.reel.domain.repository.ReelRepository
import com.develo.vectorket.reel.domain.service.Downloader
import com.develo.vectorket.reel.domain.use_case.AddToFavoritesUseCase
import com.develo.vectorket.reel.domain.use_case.DownloadUseCase
import com.develo.vectorket.reel.domain.use_case.GetFavoritesUseCase
import com.develo.vectorket.reel.domain.use_case.RemoveFromFavoritesUseCase
import com.develo.vectorket.reel.domain.use_case.UpdateDownloadUseCase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReelModule {

    @Provides
    @Singleton
    fun provideReelRepository(
        db: FirebaseFirestore,
        @Named(VECTORS_COLLECTION)
        vectors: CollectionReference,
        @Named(FAVORITES_COLLECTION)
        favorites: CollectionReference
    ): ReelRepository = ReelRepositoryImpl(db, vectors, favorites)

    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(
        repository: ReelRepository
    ) = GetFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideAddToFavoritesUseCase(
        repository: ReelRepository
    ) = AddToFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromFavoritesUseCase(
        repository: ReelRepository
    ) = RemoveFromFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideUpdateDownloadUseCase(
        repository: ReelRepository
    ) = UpdateDownloadUseCase(repository)

    @Provides
    @Singleton
    fun provideDownloader(
        @ApplicationContext context: Context
    ): Downloader = DownloaderImpl(context)

    @Provides
    @Singleton
    fun provideDownloadUseCase(
        downloader: Downloader
    ) = DownloadUseCase(downloader)
}