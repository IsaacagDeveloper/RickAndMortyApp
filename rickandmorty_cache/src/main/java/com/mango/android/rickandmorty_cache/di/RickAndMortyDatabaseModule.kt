package com.mango.android.rickandmorty_cache.di

import android.content.Context
import androidx.room.Room
import com.mango.android.rickandmorty_cache.database.RickAndMortyDao
import com.mango.android.rickandmorty_cache.database.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): RickAndMortyDatabase =
        Room.databaseBuilder(
            app,
            RickAndMortyDatabase::class.java,
            "rick_and_morty_database.db"
        ).build()

    @Provides
    @Singleton
    fun provideRickAndMortyDao(db: RickAndMortyDatabase) : RickAndMortyDao = db.rickAndMortyDao()

}