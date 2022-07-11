package com.mango.android.data.di

import com.mango.android.data.mappers.CharactersMapper
import com.mango.android.data.mappers.CurrentPageMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryMappersModule {

    @Provides
    fun providesCharactersMapper(): CharactersMapper =
        CharactersMapper()

    @Provides
    fun providesCurrentPageMapper(): CurrentPageMapper =
        CurrentPageMapper()

}