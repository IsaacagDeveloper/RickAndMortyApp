package com.mango.android.presentation.features.characterslist.di

import com.mango.android.presentation.features.characterslist.mappers.CharactersUIMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterUIMapperModule {

    @Provides
    @Singleton
    fun providesCharacterUIMapper(): CharactersUIMapper =
        CharactersUIMapper()

}