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

    fun getCurrentPage(listID: Int) = viewModelScope.launch {
            repository.getCurrentPage(listID)
                .fold(::handleFailure, ::handleCurrentPage)
        }

    private fun handleCurrentPage(currentPage: Int) {
        _currentPageLiveData.postValue(currentPage)
    }

    fun userRequireGetNewCharacters(listID: Int) {
        currentPageLiveData.value?.let {
            getCharacters(listID, it.plus(1))
        } ?: run {
            userRequireRefreshCharactersList(listID)
        }
    }

    fun userRequireRefreshCharactersList(listID: Int) {
        getCharacters(listID, FIRST_PAGE)
    }

    fun getCharacters(listID: Int, page: Int) = viewModelScope.launch {
        repository.getCharactersList(listID, page)
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
    }

}