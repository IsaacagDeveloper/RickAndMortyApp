package com.mango.android.presentation.features.characterslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mango.android.core.core.FIRST_PAGE
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

private const val CHARACTERS_LIST_ID = 1

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val repository: RickAndMortyCharactersRepository,
    private val charactersUIMapper: CharactersUIMapper
) : ViewModel(), FailureViewModel by FailureViewModelImpl() {

    private val _charactersListLiveData = MutableLiveData<List<CharacterUIModel>>()
    val charactersListLiveData: LiveData<List<CharacterUIModel>>
        get() = _charactersListLiveData

    private val _currentPageLiveData = MutableLiveData<Int>()
    val currentPageLiveData: LiveData<Int>
        get() = _currentPageLiveData

    init {
        getCurrentPage()
    }

    fun getCurrentPage() = viewModelScope.launch {
        repository.getCurrentPage(CHARACTERS_LIST_ID)
            .fold(::handleFailure, ::handleCurrentPage)
    }

    private fun handleCurrentPage(currentPage: Int) {
        _currentPageLiveData.postValue(currentPage)
    }

    fun userRequireGetNewCharacters() {
        currentPageLiveData.value?.let {
            getCharacters(it.plus(1))
        } ?: run {
            userRequireRefreshCharactersList()
        }
    }

    fun userRequireRefreshCharactersList() {
        getCharacters(FIRST_PAGE)
    }

    fun getCharacters(page: Int) = viewModelScope.launch {
        repository.getCharactersList(CHARACTERS_LIST_ID, page)
            .fold(::handleFailure, ::handleCharactersList)
    }

    private fun handleCharactersList(collection: List<Character>) {
        if (charactersListLiveData.value != null) {
            _charactersListLiveData.postValue(
                _charactersListLiveData.value?.plus(collection.map {
                    charactersUIMapper.fromCharacterDomainModelToCharacterUIModel(it)
                })
            )
        } else {
            _charactersListLiveData.postValue(
                collection.map {
                    charactersUIMapper.fromCharacterDomainModelToCharacterUIModel(it)
                }
            )
        }

        getCurrentPage()
    }

}