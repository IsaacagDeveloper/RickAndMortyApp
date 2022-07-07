package com.mango.android.rickandmorty_network.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyApiResponse(
    @SerializedName("info")
    val infoRequest: InfoRequestApiResponse,
    @SerializedName("results")
    val charactersList: List<CharacterApiResponse>,
)
