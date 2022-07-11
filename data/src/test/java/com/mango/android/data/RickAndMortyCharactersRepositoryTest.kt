package com.mango.android.data

import com.mango.android.core.core.Either
import com.mango.android.data.core.*
import com.mango.android.data.mappers.CharactersMapper
import com.mango.android.data.mappers.CurrentPageMapper
import com.mango.android.data.repositories.RickAndMortyCharactersRepositoryImpl
import com.mango.android.rickandmorty_cache.source.RickAndMortyCacheDataSource
import com.mango.android.rickandmorty_network.source.RickAndMortyNetworkDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers

@ExperimentalCoroutinesApi
class RickAndMortyCharactersRepositoryTest {

    @get:Rule
    val coroutineScope = CoroutineScopeRule()

    @RelaxedMockK
    private lateinit var rickAndMortyNetworkDataSource: RickAndMortyNetworkDataSource

    @RelaxedMockK
    private lateinit var rickAndMortyCacheDataSource: RickAndMortyCacheDataSource

    @RelaxedMockK
    private lateinit var currentPageMapper: CurrentPageMapper

    @RelaxedMockK
    private lateinit var charactersMapper: CharactersMapper

    private lateinit var repository: RickAndMortyCharactersRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = RickAndMortyCharactersRepositoryImpl(
            rickAndMortyNetworkDataSource,
            rickAndMortyCacheDataSource,
            currentPageMapper,
            charactersMapper,
            coroutineScope.testDispatcherProvider
        )
    }

    @Test
    fun `when remote source succeeds we get a success response`() = coroutineScope.runBlocking {
        //Given
        coEvery { rickAndMortyNetworkDataSource.getCharactersList(any()) } returns fakeCharactersListApiResponse

        //When
        val result =
            repository.getCharactersList(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())

        //Then
        coVerify(exactly = 1) { rickAndMortyNetworkDataSource.getCharactersList(any()) }
        result shouldBeEqualTo Either.Right(fakeCharactersListApiResponse.charactersList.map { charactersMapper.fromCharactersApiResponseToCharacterDomainModel(it) })
    }

    @Test
    fun `when remote source fails we throw an exception`() = coroutineScope.runBlocking {
        //Given
        coEvery { rickAndMortyNetworkDataSource.getCharactersList(any()) } throws fakeException

        //When
        val result =
            repository.getCharactersList(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())

        //Then
        result shouldBeEqualTo Either.Left(fakeFailure)
    }

    @Test
    fun `when local source succeeds we get a success response`() = coroutineScope.runBlocking {
        //Given
        coEvery { rickAndMortyCacheDataSource.getCurrentPageForListID(FAKE_CHARACTERS_LIST_ID) } returns fakeCurrentPageDbModel

        //When
        val result = repository.getCurrentPage(FAKE_CHARACTERS_LIST_ID)

        //Then
        result shouldBeEqualTo Either.Right(currentPageMapper.fromCurrentPageDbModelToPage(fakeCurrentPageDbModel))
    }

    @Test
    fun `when local source fails we throw an exception`() = coroutineScope.runBlocking {
        //Given
        coEvery { rickAndMortyCacheDataSource.getCurrentPageForListID(FAKE_CHARACTERS_LIST_ID) } throws fakeException

        //When
        val result = repository.getCurrentPage(FAKE_CHARACTERS_LIST_ID)

        //Then
        result shouldBeEqualTo Either.Left(fakeFailure)
    }

}