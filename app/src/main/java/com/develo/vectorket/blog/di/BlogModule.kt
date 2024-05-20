package com.develo.vectorket.blog.di

import com.develo.vectorket.blog.data.repository.BlogRepositoryImpl
import com.develo.vectorket.blog.domain.repository.BlogRepository
import com.develo.vectorket.blog.domain.use_case.GetLatestBlogsUseCase
import com.develo.vectorket.core.data.util.BLOGS_COLLECTION
import com.google.firebase.firestore.CollectionReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BlogModule {

    @Provides
    @Singleton
    fun provideBlogRepository(
        @Named(BLOGS_COLLECTION)
        blogs: CollectionReference
    ): BlogRepository = BlogRepositoryImpl(blogs)

    @Provides
    @Singleton
    fun provideGetLatestBlogsUseCase(
        repository: BlogRepository
    ) = GetLatestBlogsUseCase(repository)
}