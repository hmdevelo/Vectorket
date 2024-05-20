package com.develo.vectorket.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.develo.vectorket.core.data.repository.DataStoreRepositoryImpl
import com.develo.vectorket.core.domain.repository.DataStoreRepository
import com.develo.vectorket.core.domain.repository.DataStoreRepository.Companion.DATA_STORE_KEY
import com.develo.vectorket.core.domain.use_case.GetPrefsUseCase
import com.develo.vectorket.core.domain.use_case.SavePrefsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        produceFile = { context.preferencesDataStoreFile(DATA_STORE_KEY) }
    )

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        dataStore: DataStore<Preferences>
    ): DataStoreRepository = DataStoreRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun provideGetPrefsUseCase(
        repository: DataStoreRepository
    ) = GetPrefsUseCase(repository)

    @Provides
    @Singleton
    fun provideSavePrefsUseCase(
        repository: DataStoreRepository
    ) = SavePrefsUseCase(repository)
}