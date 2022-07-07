package com.isaacag.data.repositories

import com.isaacag.core.core.Either
import com.isaacag.core.core.Failure
import com.isaacag.data.dispatcher.CoroutineDispatcherProvider
import com.isaacag.data.mappers.CharactersMapper
import com.isaacag.domain.models.Character
import com.isaacag.domain.repositories.RickAndMortyCharactersRepository
import com.isaacag.rickandmorty_network.source.RickAndMortyNetworkDataSource
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