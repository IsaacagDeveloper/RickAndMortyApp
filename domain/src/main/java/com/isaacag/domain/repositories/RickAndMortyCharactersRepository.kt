package com.isaacag.domain.repositories

import com.isaacag.core.core.Either
import com.isaacag.core.core.Failure
import com.isaacag.domain.models.Character

interface RickAndMortyCharactersRepository {
    suspend fun getCharactersList(page: Int): Either<Failure, List<Character>>
    suspend fun getCharacterDetailInformation(characterID: Int): Either<Failure, Character>
}