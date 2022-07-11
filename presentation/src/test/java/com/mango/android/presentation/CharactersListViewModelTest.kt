package com.mango.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mango.android.core.core.Either
import com.mango.android.core.core.FIRST_PAGE
import com.mango.android.domain.repositories.RickAndMortyCharactersRepository
import com.mango.android.presentation.core.*
import com.mango.android.presentation.features.characterslist.mappers.CharactersUIMapper
import com.mango.android.presentation.features.characterslist.viewmodel.CharactersListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class CharactersListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = CoroutineScopeRule()

    @RelaxedMockK
    private lateinit var repository: RickAndMortyCharactersRepository

    @RelaxedMockK
    private lateinit var charactersUIMapper: CharactersUIMapper

    private lateinit var viewModel: CharactersListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharactersListViewModel(
            repository,
            charactersUIMapper
        )
    }

    @Test
    fun `when we ask a repository to get all characters and succeeds we get either right response`() = coroutineScope.runBlocking {
        // Given
        coEvery {
            repository.getCharactersList(FAKE_CHARACTERS_LIST_ID, FAKE_CURRENT_PAGE)
        } returns Either.Right(fakeCharactersListDomainModelFromApiResponse)

        // When
        val liveDataUnderTest = viewModel.charactersListLiveData.testObserver()
        viewModel.userRequireRefreshCharactersList(FAKE_CHARACTERS_LIST_ID)

        // Then
        with(liveDataUnderTest.observedValues) {
            size shouldBeEqualTo 1
            get(0) shouldBeEqualTo fakeCharactersListDomainModelFromApiResponse.map { charactersUIMapper.fromCharacterDomainModelToCharacterUIModel(it) }
        }
    }

    @Test
    fun `when we ask a repository to get all characters and fails we get either left response`() = coroutineScope.runBlocking {
        // Given
        coEvery {
            repository.getCharactersList(FAKE_CHARACTERS_LIST_ID, FAKE_CURRENT_PAGE)
        } returns Either.Left(fakeFailure)

        // When
        val liveDataUnderTest = viewModel.failure.testObserver()
        viewModel.userRequireRefreshCharactersList(FAKE_CHARACTERS_LIST_ID)

        // Then
        with(liveDataUnderTest.observedValues) {
            size shouldBeEqualTo 1
            get(0) shouldBeEqualTo fakeFailure
        }
    }

    @Test
    fun `when we ask a repository to get the current page and succeeds we get either right response`() = coroutineScope.runBlocking {
        // Given
        coEvery {
            repository.getCurrentPage(FAKE_CHARACTERS_LIST_ID)
        } returns Either.Right(FIRST_PAGE)

        // When
        val liveDataUnderTest = viewModel.currentPageLiveData.testObserver()
        viewModel.getCurrentPage(FAKE_CHARACTERS_LIST_ID)

        // Then
        with(liveDataUnderTest.observedValues) {
            size shouldBeEqualTo 1
            get(0) shouldBeEqualTo FIRST_PAGE
        }
    }

    @Test
    fun `when we ask a repository to get the current page and fails we get either left response`() = coroutineScope.runBlocking {
        // Given
        coEvery {
            repository.getCurrentPage(FAKE_CHARACTERS_LIST_ID)
        } returns Either.Left(fakeFailure)

        // When
        val liveDataUnderTest = viewModel.failure.testObserver()
        viewModel.getCurrentPage(FAKE_CHARACTERS_LIST_ID)

        // Then
        with(liveDataUnderTest.observedValues) {
            size shouldBeEqualTo 1
            get(0) shouldBeEqualTo fakeFailure
        }
    }
}