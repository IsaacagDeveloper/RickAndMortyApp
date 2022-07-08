package com.mango.android.presentation.features.characterslist.models

data class CharacterUIModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val imageUrl: String
)