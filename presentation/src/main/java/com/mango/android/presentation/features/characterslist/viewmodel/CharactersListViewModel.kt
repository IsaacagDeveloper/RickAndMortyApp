package com.mango.android.presentation.features.characterslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mango.android.core.core.fold
import com.mango.android.domain.models.Character
import com.mango.android.domain.repositories.RickAndMortyCharactersRepository
import com.mango.android.presentation.features.characterslist.mappers.CharactersUIMapper
import com.mango.android.presentation.features.characterslist.models.CharacterUIModel
import com.mango.android.presentation.viewmodels.failure.FailureViewModel
import com.mango.android.presentation.viewmodels.failure.FailureViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val repository: RickAndMortyCharactersRepository,
    private val charactersUIMapper: CharactersUIMapper
): ViewModel(), FailureViewModel by FailureViewModelImpl() {

    private val _charactersListLiveData = MutableLiveData<List<CharacterUIModel>>()
    val charactersListLiveData: LiveData<List<CharacterUIModel>>
        get() = _charactersListLiveData

    //TODO refactor when apply pagination
    fun userRequireCharactersList() = viewModelScope.launch {
        repository.getCharactersList(1)
            .fold(::handleFailure, ::handleCharactersList)
    }

    private fun handleCharactersList(collection: List<Character>) {
        _charactersListLiveData.postValue(
            collection.map {
                charactersUIMapper.fromCharacterDomainModelToCharacterUIModel(it)
            }
        )
    }

}