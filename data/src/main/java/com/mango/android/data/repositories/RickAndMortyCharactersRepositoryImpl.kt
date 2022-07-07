package com.mango.android.data.repositories

import com.mango.android.core.core.Either
import com.mango.android.core.core.Failure
import com.mango.android.data.dispatcher.CoroutineDispatcherProvider
import com.mango.android.data.mappers.CharactersMapper
import com.mango.android.domain.models.Character
import com.mango.android.domain.repositories.RickAndMortyCharactersRepository
import com.mango.android.rickandmorty_network.source.RickAndMortyNetworkDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickAndMortyCharactersRepositoryImpl @Inject constructor(
    private val rickAndMortyNetworkDataSource: RickAndMortyNetworkDataSource,
    private val charactersMapper: CharactersMapper,
    private val coroutineDispatcher: CoroutineDispatcherProvider
): RickAndMortyCharactersRepository {

    override suspend fun getCharactersList(page: Int): Either<Failure, List<Character>> =
        withContext(coroutineDispatcher.io) {
            try {
                val result = rickAndMortyNetworkDataSource.getCharactersList(page)
                if (result.charactersList.isNotEmpty()) {
                    Either.Right(result.charactersList.map {
                        charactersMapper.fromCharactersApiResponseToCharacterDomainModel(it)
                    })
                } else {
                    Either.Left(Failure.EmptyResponse)
                }
            } catch (exception: Exception) {
                Either.Left(Failure.ServerError)
            }
        }

    override suspend fun getCharacterDetailInformation(characterID: Int): Either<Failure, Character> =
        withContext(coroutineDispatcher.io) {
            try {
                val result = rickAndMortyNetworkDataSource.getCharacterDetail(characterID)
                if (result.id > 0) {
                    Either.Right(charactersMapper.fromCharactersApiResponseToCharacterDomainModel(result))
                } else {
                    Either.Left(Failure.EmptyResponse)
                }
            } catch (exception: Exception) {
                Either.Left(Failure.ServerError)
            }
        }


}