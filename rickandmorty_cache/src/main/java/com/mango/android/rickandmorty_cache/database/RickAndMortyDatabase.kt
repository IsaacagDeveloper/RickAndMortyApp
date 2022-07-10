package com.mango.android.rickandmorty_cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mango.android.rickandmorty_cache.models.CurrentPageDbModel

@Database(entities = [CurrentPageDbModel::class], version = 1)
abstract class RickAndMortyDatabase: RoomDatabase() {
    abstract fun rickAndMortyDao(): RickAndMortyDao
}