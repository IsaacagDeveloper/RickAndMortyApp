package com.isaacag.rickandmorty_network.models

import com.google.gson.annotations.SerializedName

data class CharacterApiResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginApiResponse,
    val location: CharacterLocationApiResponse,
    val image: String,
    @SerializedName("episode")
    val episodesList: List<String>,
    val url: String,
    val created: String
)