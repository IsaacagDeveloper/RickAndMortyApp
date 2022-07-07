package com.isaacag.data.mappers

import com.isaacag.domain.models.Character
import com.isaacag.rickandmorty_network.models.CharacterApiResponse

class CharactersMapper {

    fun fromCharactersApiResponseToCharacterDomainModel(
        characterApiResponse: CharacterApiResponse
    ): Character =
        Character(
            id = characterApiResponse.id,
            name = characterApiResponse.name,
            status = characterApiResponse.status,
            species = characterApiResponse.species,
            type = characterApiResponse.type,
            gender = characterApiResponse.gender,
            origin = characterApiResponse.origin.name,
            location = characterApiResponse.location.name,
            image = characterApiResponse.image,
            created = characterApiResponse.created,
        )

}