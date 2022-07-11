package com.mango.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mango.android.core.core.Either
import com.mango.android.domain.repositories.RickAndMortyCharactersRepository
import com.mango.android.presentation.core.*
import com.mango.android.presentation.features.characterdetail.viewmodel.CharacterDetailViewModel
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
class CharacterDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = CoroutineScopeRule()

    @RelaxedMockK
    private lateinit var repository: RickAndMortyCharactersRepository

    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CharacterDetailViewModel(
            repository
        )
    }

    @Test
    fun `when we ask a repository to get character detail info and succeeds we get either right response`() = coroutineScope.runBlocking {
        // Given
        coEvery {
            repository.getCharacterDetailInformation(FAKE_CHARACTER_ID)
        } returns Either.Right(fakeCharacterDomainModelFromApi)

        // When
        val liveDataUnderTest = viewModel.characterDetailInfoLiveData.testObserver()
        viewModel.userRequireDetailInformationOfCharacter(FAKE_CHARACTER_ID)

        // Then
        with(liveDataUnderTest.observedValues) {
            size shouldBeEqualTo 1
            get(0) shouldBeEqualTo fakeCharacterDomainModelFromApi
        }
    }

    @Test
    fun `when we ask a repository to get character detail info and fails we get either left response`() = coroutineScope.runBlocking {
        // Given
        coEvery {
            repository.getCharacterDetailInformation(FAKE_CHARACTER_ID)
        } returns Either.Left(fakeFailure)

        // When
        val liveDataUnderTest = viewModel.failure.testObserver()
        viewModel.userRequireDetailInformationOfCharacter(FAKE_CHARACTER_ID)

        // Then
        with(liveDataUnderTest.observedValues) {
            size shouldBeEqualTo 1
            get(0) shouldBeEqualTo fakeFailure
        }
    }

}