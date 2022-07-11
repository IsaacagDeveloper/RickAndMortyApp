package com.mango.android.rickandmorty_network

import com.mango.android.rickandmorty_network.models.CharacterApiResponse
import com.mango.android.rickandmorty_network.models.RickAndMortyApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/")
    suspend fun getRickAndMortyCharacters(
        @Query("page") page: Int
    ): RickAndMortyApiResponse

    @GET("character/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int
    ): CharacterApiResponse

}