package com.mango.android.data

import com.mango.android.data.dispatcher.CoroutineDispatcherProvider
import com.mango.android.data.mappers.CharactersMapper
import com.mango.android.data.mappers.CurrentPageMapper
import com.mango.android.data.repositories.RickAndMortyCharactersRepositoryImpl
import com.mango.android.rickandmorty_cache.source.RickAndMortyCacheDataSource
import com.mango.android.rickandmorty_network.source.RickAndMortyNetworkDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineDispatcher
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

    @RelaxedMockK
    private lateinit var coroutinesDispatcher: CoroutineDispatcherProvider

    private lateinit var repository: RickAndMortyCharactersRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = RickAndMortyCharactersRepositoryImpl(
            rickAndMortyNetworkDataSource,
            rickAndMortyCacheDataSource,
            currentPageMapper,
            charactersMapper,
            coroutinesDispatcher
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
        result shouldBeEqualTo fakeCharactersListDomainModelFromApiResponse
    }

    @Test(expected = Exception::class)
    fun `when remote source fails we throw an exception`() = coroutineScope.runBlocking {
        //Given
        coEvery { rickAndMortyNetworkDataSource.getCharactersList(any()) } throws fakeException

        //When
        val result =
            repository.getCharactersList(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())

        //Then
        coVerify(exactly = 0) { rickAndMortyNetworkDataSource.getCharactersList(any()) }
        result shouldBeEqualTo fakeException
    }

    @Test
    fun `when local source succeeds we get a success response`() = coroutineScope.runBlocking {
        //Given
        coEvery { rickAndMortyCacheDataSource.getCurrentPageForListID(FAKE_CHARACTERS_LIST_ID) } returns fakeCurrentPageDbModel

        //When
        val result = repository.getCharactersList(FAKE_CHARACTERS_LIST_ID, FAKE_CURRENT_PAGE)

        //Then
        coVerify(exactly = 1) {
            rickAndMortyCacheDataSource.getCurrentPageForListID(
                FAKE_CHARACTERS_LIST_ID
            )
        }
        result shouldBeEqualTo fakeCurrentPageDbModel
    }

    @Test(expected = Exception::class)
    fun `when local source fails we throw an exception`() = coroutineScope.runBlocking {
        //Given
        coEvery { rickAndMortyCacheDataSource.getCurrentPageForListID(FAKE_CHARACTERS_LIST_ID) } throws fakeException

        //When
        val result = repository.getCharactersList(FAKE_CHARACTERS_LIST_ID, FAKE_CURRENT_PAGE)

        //Then
        coVerify(exactly = 0) {
            rickAndMortyCacheDataSource.getCurrentPageForListID(
                FAKE_CHARACTERS_LIST_ID
            )
        }
        result shouldBeEqualTo fakeException
    }

}