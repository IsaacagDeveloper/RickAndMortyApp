package com.isaacag.rickandmorty_network.di

import com.isaacag.rickandmorty_network.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyUrlModule {

    @Provides
    @Singleton
    fun provideOompaLoompaApi(retrofit: Retrofit): RickAndMortyService =
        retrofit.create(RickAndMortyService::class.java)

}