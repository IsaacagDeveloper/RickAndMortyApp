package com.mango.android.presentation.core

import com.mango.android.core.core.Failure
import com.mango.android.domain.models.Character
import com.mango.android.presentation.features.characterslist.models.CharacterUIModel

/**
 * Domain Response and models
 */

val fakeCharacterDomainModelFromApi = Character(
    1,
    "Rick Sanchez",
    "Alive",
    "Human",
    "Male",
    "Earth (C-137)",
    "Citadel of Ricks",
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    "2017-11-04T18:48:46.250Z"
)

val fakeCharactersListDomainModelFromApiResponse = listOf(fakeCharacterDomainModelFromApi)

/**
 * UI Response and models
 */

val fakeCurrentPageModel = 1

const val FAKE_CHARACTERS_LIST_ID = 1434
const val FAKE_CURRENT_PAGE = 1

val fakeCharacterUIModel = CharacterUIModel(
    1,
    "Rick Sanchez",
    "Alive",
    "Human",
    "Male",
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
)

const val FAKE_CHARACTER_ID = 1

val fakeCharactersListUIModel = listOf(fakeCharacterUIModel)

/**
 * Error models
 */
val fakeFailure = Failure.ServerError