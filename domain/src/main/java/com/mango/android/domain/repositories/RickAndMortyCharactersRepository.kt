package com.mango.android.domain.repositories

import com.mango.android.core.core.Either
import com.mango.android.core.core.Failure
import com.mango.android.domain.models.Character

interface RickAndMortyCharactersRepository {
    //REMOTE SOURCE
    suspend fun getCharactersList(listID: Int, page: Int): Either<Failure, List<Character>>
    suspend fun getCharacterDetailInformation(characterID: Int): Either<Failure, Character>

    //LOCAL SOURCE
    suspend fun getCurrentPage(listID: Int): Either<Failure, Int>
    suspend fun insertListCurrentPageToDB(listID: Int, currentPage: Int)
    suspend fun deleteAllListsCurrentPagesFromDB()
}