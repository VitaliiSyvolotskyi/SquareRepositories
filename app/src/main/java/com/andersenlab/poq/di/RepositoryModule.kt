package com.andersenlab.poq.di

import com.andersenlab.poq.data.api.RepositoriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.andersenlab.poq.domain.RepositoriesUseCase
import com.andersenlab.poq.domain.RepositoriesUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
   @Singleton
    @Provides
    fun provideDataRepositoryUseCase(
        api: RepositoriesApi
    ): RepositoriesUseCase = RepositoriesUseCaseImpl(api)
}