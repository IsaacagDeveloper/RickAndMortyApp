package com.mango.android.data.di

import com.mango.android.core.dispatcher.DefaultCoroutineDispatcherProvider
import com.mango.android.data.mappers.CharactersMapper
import com.mango.android.data.mappers.CurrentPageMapper
import com.mango.android.data.repositories.RickAndMortyCharactersRepositoryImpl
import com.mango.android.domain.repositories.RickAndMortyCharactersRepository
import com.mango.android.rickandmorty_cache.source.RickAndMortyCacheDataSource
import com.mango.android.rickandmorty_network.source.RickAndMortyNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyCharactersRepositoryModule {

    @Provides
    fun getRickAndMortyCharactersRepository(
        rickAndMortyNetworkDataSource: RickAndMortyNetworkDataSource,
        rickAndMortyCacheDataSource: RickAndMortyCacheDataSource
    ): RickAndMortyCharactersRepository = RickAndMortyCharactersRepositoryImpl(
        rickAndMortyNetworkDataSource = rickAndMortyNetworkDataSource,
        rickAndMortyCacheDataSource = rickAndMortyCacheDataSource,
        currentPageMapper = CurrentPageMapper(),
        charactersMapper = CharactersMapper(),
        coroutineDispatcher = DefaultCoroutineDispatcherProvider()
    )

}