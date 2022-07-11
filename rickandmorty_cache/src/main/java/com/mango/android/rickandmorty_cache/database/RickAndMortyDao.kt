package com.mango.android.rickandmorty_cache.database

import androidx.room.*
import com.mango.android.rickandmorty_cache.models.CurrentPageDbModel

@Dao
abstract class RickAndMortyDao {

    @Query("SELECT * FROM current_page_cached WHERE listID = :listIDSent")
    abstract suspend fun getCurrentPageForListID(listIDSent: Int): CurrentPageDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertListCurrentPage(currentPageDbModel: CurrentPageDbModel)

    @Query("DELETE FROM current_page_cached")
    abstract suspend fun deleteAllListsCurrentPages()

}