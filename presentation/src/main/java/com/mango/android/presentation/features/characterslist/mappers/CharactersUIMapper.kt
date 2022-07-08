package com.mango.android.presentation.features.characterslist.mappers

import com.mango.android.domain.models.Character
import com.mango.android.presentation.features.characterslist.models.CharacterUIModel

class CharactersUIMapper {
    
    fun fromCharacterDomainModelToCharacterUIModel(
        character: Character
    ): CharacterUIModel =
        CharacterUIModel(
            id = character.id,
            name = character.name,
            status = character.status,
            species = character.species,
            gender = character.gender,
            imageUrl = character.image
        )
}