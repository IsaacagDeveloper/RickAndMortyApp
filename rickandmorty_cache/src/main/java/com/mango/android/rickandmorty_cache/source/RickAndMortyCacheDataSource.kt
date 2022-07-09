package com.mango.android.rickandmorty_cache.source

import com.mango.android.rickandmorty_cache.database.RickAndMortyDatabase
import com.mango.android.rickandmorty_cache.models.CurrentPageDbModel
import javax.inject.Inject


class RickAndMortyCacheDataSource @Inject constructor(private val db: RickAndMortyDatabase) {

    suspend fun getCurrentPageForListID(listIDSent: Int) = db.rickAndMortyDao().getCurrentPageForListID(listIDSent)

    suspend fun insertListCurrentPageToDB(currentPageDbModel: CurrentPageDbModel) =
        db.rickAndMortyDao().insertListCurrentPage(currentPageDbModel)

    suspend fun deleteAllListsCurrentPagesFromDB() = db.rickAndMortyDao().deleteAllListsCurrentPages()

}