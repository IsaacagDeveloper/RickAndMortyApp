package com.mango.android.data.repositories

import com.mango.android.core.core.Either
import com.mango.android.core.core.Failure
import com.mango.android.data.dispatcher.CoroutineDispatcherProvider
import com.mango.android.data.mappers.CharactersMapper
import com.mango.android.data.mappers.CurrentPageMapper
import com.mango.android.domain.models.Character
import com.mango.android.domain.repositories.RickAndMortyCharactersRepository
import com.mango.android.rickandmorty_cache.source.RickAndMortyCacheDataSource
import com.mango.android.rickandmorty_network.source.RickAndMortyNetworkDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickAndMortyCharactersRepositoryImpl @Inject constructor(
    private val rickAndMortyNetworkDataSource: RickAndMortyNetworkDataSource,
    private val rickAndMortyCacheDataSource: RickAndMortyCacheDataSource,
    private val currentPageMapper: CurrentPageMapper,
    private val charactersMapper: CharactersMapper,
    private val coroutineDispatcher: CoroutineDispatcherProvider
): RickAndMortyCharactersRepository {

    /**
     * NETWORK DATASOURCE
     */

    override suspend fun getCharactersList(listID: Int, page: Int): Either<Failure, List<Character>> =
        withContext(coroutineDispatcher.io) {
            try {
                val result = rickAndMortyNetworkDataSource.getCharactersList(page)
                if (result.charactersList.isNotEmpty()) {
                    insertListCurrentPageToDB(listID, page)
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

    /**
     * LOCAL DATASOURCE
     */

    override suspend fun getCurrentPage(listID: Int): Either<Failure, Int> =
        withContext(coroutineDispatcher.io) {
            try {
                val result = rickAndMortyCacheDataSource.getCurrentPageForListID(listID)
                if (result.currentPage != 0) {
                    Either.Right(currentPageMapper.fromCurrentPageDbModelToPage(result))
                } else {
                    Either.Left(Failure.EmptyResponse)
                }
            } catch (exception: Exception) {
                Either.Left(Failure.ServerError)
            }
        }

    override suspend fun insertListCurrentPageToDB(listID: Int, currentPage: Int) =
        rickAndMortyCacheDataSource.insertListCurrentPageToDB(
            currentPageMapper.fromListIDAndPageDomainModelToCurrentPageDbModel(listID, currentPage)
        )

    override suspend fun deleteAllListsCurrentPagesFromDB() =
        rickAndMortyCacheDataSource.deleteAllListsCurrentPagesFromDB()
}