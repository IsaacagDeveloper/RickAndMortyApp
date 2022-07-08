package com.mango.android.presentation.features.characterdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mango.android.core.core.fold
import com.mango.android.domain.models.Character
import com.mango.android.domain.repositories.RickAndMortyCharactersRepository
import com.mango.android.presentation.viewmodels.failure.FailureViewModel
import com.mango.android.presentation.viewmodels.failure.FailureViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: RickAndMortyCharactersRepository
): ViewModel(), FailureViewModel by FailureViewModelImpl() {

    private val _characterDetailInfoLiveData = MutableLiveData<Character>()
    val characterDetailInfoLiveData: LiveData<Character>
        get() = _characterDetailInfoLiveData

    fun userRequireDetailInformationOfCharacter(characterID: Int) = viewModelScope.launch() {
        repository.getCharacterDetailInformation(characterID)
            .fold(::handleFailure, ::handleCharacterDetailInformationReceived)
    }

    private fun handleCharacterDetailInformationReceived(character: Character) {
        _characterDetailInfoLiveData.postValue(character)
    }

}