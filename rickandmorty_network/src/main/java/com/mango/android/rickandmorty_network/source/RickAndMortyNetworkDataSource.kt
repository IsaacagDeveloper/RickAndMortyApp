package com.mango.android.rickandmorty_network.source

import com.mango.android.rickandmorty_network.RickAndMortyService
import com.mango.android.rickandmorty_network.models.CharacterApiResponse
import com.mango.android.rickandmorty_network.models.RickAndMortyApiResponse
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