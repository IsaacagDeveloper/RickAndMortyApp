package com.isaacag.rickandmorty_network.source

import com.isaacag.rickandmorty_network.RickAndMortyService
import com.isaacag.rickandmorty_network.models.CharacterApiResponse
import com.isaacag.rickandmorty_network.models.RickAndMortyApiResponse
import javax.inject.Inject

class RickAndMortyNetworkDataSource @Inject constructor(private val service: RickAndMortyService) {

    suspend fun getCharactersList(
        page: Int
    ): RickAndMortyApiResponse {
        return service.getRickAndMortyCharacters(
            page = page
        )
    }

    suspend fun getCharacterDetail(
        characterId: Int
    ): CharacterApiResponse {
        return service.getCharacterDetail(
            characterId = characterId
        )
    }

}