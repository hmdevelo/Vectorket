package com.develo.vectorket.core.di

import androidx.paging.PagingConfig
import com.develo.vectorket.core.data.query.QueryBuilder
import com.develo.vectorket.core.data.service.RandomServiceImpl
import com.develo.vectorket.core.data.util.BLOGS_COLLECTION
import com.develo.vectorket.core.data.util.FAVORITES_COLLECTION
import com.develo.vectorket.core.data.util.PAGE_SIZE
import com.develo.vectorket.core.data.util.VECTORS_COLLECTION
import com.develo.vectorket.core.domain.service.RandomService
import com.develo.vectorket.core.domain.use_case.GetPagingSourceUseCase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestoreDatabase() = Firebase.firestore

    @Provides
    @Singleton
    @Named(VECTORS_COLLECTION)
    fun provideVectorsCollection(
        db: FirebaseFirestore
    ) = db.collection(VECTORS_COLLECTION)

    @Provides
    @Singleton
    @Named(FAVORITES_COLLECTION)
    fun provideFavoritesCollection(
        db: FirebaseFirestore
    ) = db.collection(FAVORITES_COLLECTION)

    @Provides
    @Singleton
    @Named(BLOGS_COLLECTION)
    fun provideBlogsCollection(
        db: FirebaseFirestore
    ) = db.collection(BLOGS_COLLECTION)

    @Provides
    @Singleton
    fun providePagingConfig() = PagingConfig(pageSize = PAGE_SIZE.toInt())

    @Provides
    @Singleton
    fun provideGetPagingSourceUseCase() = GetPagingSourceUseCase()

    @Provides
    @Singleton
    fun provideRandomService(
        @Named(VECTORS_COLLECTION)
        vectors: CollectionReference
    ): RandomService = RandomServiceImpl(vectors)

    @Provides
    @Singleton
    fun provideQueryBuilder(
        @Named(VECTORS_COLLECTION)
        vectors: CollectionReference
    ) = QueryBuilder(vectors)
}