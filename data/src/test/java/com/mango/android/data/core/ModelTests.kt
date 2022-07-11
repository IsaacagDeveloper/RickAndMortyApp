package com.mango.android.data.core

import com.mango.android.domain.models.Character
import com.mango.android.rickandmorty_cache.models.CurrentPageDbModel
import com.mango.android.rickandmorty_network.models.*

/**
 * API Response and models
 */

val fakeCharacterLocationApiResponse = CharacterLocationApiResponse(
    "Citadel of Ricks",
    "https://rickandmortyapi.com/api/location/3"
)

val fakeCharacterOriginApiResponse = CharacterOriginApiResponse(
    "Earth (C-137)",
    "https://rickandmortyapi.com/api/location/1"
)

val fakeCharacterApiResponse = CharacterApiResponse(
    1,
    "Rick Sanchez",
    "Alive",
    "Human",
    "",
    "Male",
    fakeCharacterOriginApiResponse,
    fakeCharacterLocationApiResponse,
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    listOf("https://rickandmortyapi.com/api/episode/1",
        "https://rickandmortyapi.com/api/episode/2",
        "https://rickandmortyapi.com/api/episode/3",
        "https://rickandmortyapi.com/api/episode/4",
        "https://rickandmortyapi.com/api/episode/5",
        "https://rickandmortyapi.com/api/episode/6",
        "https://rickandmortyapi.com/api/episode/7",
        "https://rickandmortyapi.com/api/episode/8",
        "https://rickandmortyapi.com/api/episode/9",
        "https://rickandmortyapi.com/api/episode/10",
        "https://rickandmortyapi.com/api/episode/11",
        "https://rickandmortyapi.com/api/episode/12",
        "https://rickandmortyapi.com/api/episode/13",
        "https://rickandmortyapi.com/api/episode/14",
        "https://rickandmortyapi.com/api/episode/15",
        "https://rickandmortyapi.com/api/episode/16",
        "https://rickandmortyapi.com/api/episode/17",
        "https://rickandmortyapi.com/api/episode/18",
        "https://rickandmortyapi.com/api/episode/19",
        "https://rickandmortyapi.com/api/episode/20",
        "https://rickandmortyapi.com/api/episode/21",
        "https://rickandmortyapi.com/api/episode/22",
        "https://rickandmortyapi.com/api/episode/23",
        "https://rickandmortyapi.com/api/episode/24",
        "https://rickandmortyapi.com/api/episode/25",
        "https://rickandmortyapi.com/api/episode/26",
        "https://rickandmortyapi.com/api/episode/27",
        "https://rickandmortyapi.com/api/episode/28",
        "https://rickandmortyapi.com/api/episode/29",
        "https://rickandmortyapi.com/api/episode/30",
        "https://rickandmortyapi.com/api/episode/31",
        "https://rickandmortyapi.com/api/episode/32",
        "https://rickandmortyapi.com/api/episode/33",
        "https://rickandmortyapi.com/api/episode/34",
        "https://rickandmortyapi.com/api/episode/35",
        "https://rickandmortyapi.com/api/episode/36",
        "https://rickandmortyapi.com/api/episode/37",
        "https://rickandmortyapi.com/api/episode/38",
        "https://rickandmortyapi.com/api/episode/39",
        "https://rickandmortyapi.com/api/episode/40",
        "https://rickandmortyapi.com/api/episode/41",
        "https://rickandmortyapi.com/api/episode/42",
        "https://rickandmortyapi.com/api/episode/43",
        "https://rickandmortyapi.com/api/episode/44",
        "https://rickandmortyapi.com/api/episode/45",
        "https://rickandmortyapi.com/api/episode/46",
        "https://rickandmortyapi.com/api/episode/47",
        "https://rickandmortyapi.com/api/episode/48",
        "https://rickandmortyapi.com/api/episode/49",
        "https://rickandmortyapi.com/api/episode/50",
        "https://rickandmortyapi.com/api/episode/51"),
    "https://rickandmortyapi.com/api/character/1",
    "2017-11-04T18:48:46.250Z"
)

val fakeInfoRequestApiResponse = InfoRequestApiResponse(
    826,
    42,
    "https://rickandmortyapi.com/api/character/?page=2",
    null
)

val fakeCharactersListApiResponse = RickAndMortyApiResponse(
    fakeInfoRequestApiResponse,
    listOf(fakeCharacterApiResponse)
)

/**
 * DB Response and models
 */

val fakeCurrentPageDbModel by lazy {
    CurrentPageDbModel(
        FAKE_CHARACTERS_LIST_ID,
        FAKE_CURRENT_PAGE
    )
}

const val FAKE_CHARACTERS_LIST_ID = 1434
const val FAKE_CURRENT_PAGE = 1

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
 * Error models
 */
const val fakeErrorName = "Error getting response"
val fakeException = Exception(fakeErrorName)