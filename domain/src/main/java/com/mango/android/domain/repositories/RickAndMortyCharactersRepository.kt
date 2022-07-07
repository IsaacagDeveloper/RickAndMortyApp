package com.mango.android.domain.repositories

import com.mango.android.core.core.Either
import com.mango.android.core.core.Failure
import com.mango.android.domain.models.Character

interface RickAndMortyCharactersRepository {
    suspend fun getCharactersList(page: Int): Either<Failure, List<Character>>
    suspend fun getCharacterDetailInformation(characterID: Int): Either<Failure, Character>
}