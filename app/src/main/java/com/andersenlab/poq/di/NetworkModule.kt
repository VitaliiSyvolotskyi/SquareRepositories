package com.andersenlab.poq.di

import com.andersenlab.poq.Constants
import com.andersenlab.poq.data.api.RepositoriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideRepositoryApi(
        retrofit: Retrofit
    ): RepositoriesApi = retrofit.create(RepositoriesApi::class.java)
}