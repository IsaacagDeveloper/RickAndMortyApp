package com.mango.android.rickandmorty_network

import com.mango.android.rickandmorty_network.core.*
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.mockito.ArgumentMatchers
import java.net.HttpURLConnection

class RickAndMortyNetworkDataSourceTest: TestMockWebService() {

    @Test
    fun `when we ask for the list of characters from the network service we get a success response`() {
        // Given
        val expectedResult = fakeCharactersListApiResponse
        mockHttpResponse(CHARACTERS_LIST_FAKE_JSON, HttpURLConnection.HTTP_OK)

        // When
        val response = runBlocking {
            apiService.getRickAndMortyCharacters(
                ArgumentMatchers.anyInt()
            )
        }

        // Then
        response.toString() shouldBeEqualTo expectedResult.toString()
    }

    @Test(expected = Exception::class)
    fun `when we ask for the list of characters from the network service we throw an exception`() {
        // Given
        mockHttpResponse(CHARACTERS_LIST_FAKE_JSON, HttpURLConnection.HTTP_UNAVAILABLE)

        // When
        val response = runBlocking {
            apiService.getRickAndMortyCharacters(
                ArgumentMatchers.anyInt()
            )
        }

        // Then
        response shouldBeEqualTo HttpURLConnection.HTTP_UNAVAILABLE
    }

    @Test
    fun `when we ask for the detail info of character from the network service we get a success response`() {
        // Given
        val expectedResult = fakeCharacterApiResponse
        mockHttpResponse(CHARACTER_DETAIL_INFO_FAKE_JSON, HttpURLConnection.HTTP_OK)

        // When
        val response = runBlocking {
            apiService.getCharacterDetail(
                ArgumentMatchers.anyInt()
            )
        }

        // Then
        response.toString() shouldBeEqualTo expectedResult.toString()
    }

    @Test(expected = Exception::class)
    fun `when we ask for the detail info of character from the network service we throw an exception`() {
        // Given
        mockHttpResponse(CHARACTER_DETAIL_INFO_FAKE_JSON, HttpURLConnection.HTTP_UNAVAILABLE)

        // When
        val response = runBlocking {
            apiService.getCharacterDetail(
                ArgumentMatchers.anyInt()
            )
        }

        // Then
        response shouldBeEqualTo HttpURLConnection.HTTP_UNAVAILABLE
    }

}