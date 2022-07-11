package com.mango.android.rickandmorty_network.core

import com.mango.android.rickandmorty_network.RickAndMortyService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection

abstract class TestMockWebService {

    lateinit var mockServer: MockWebServer
    lateinit var apiService: RickAndMortyService

    @Before
    open fun setUp() {
        this.configureMockServer()
        generateFakeRickAndMortyApiService()
    }

    open fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(filename: String): String {
        val resourcesDirectory = File("src/test/resources/json/${filename}")
        return String(resourcesDirectory.readBytes())
    }

    private fun generateFakeRickAndMortyApiService() {
        apiService = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .client(generateOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyService::class.java)
    }

    private fun generateOkHttpClient() = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(ErrorInterceptor())
        .build()

    open fun stopMockServer() {
        mockServer.shutdown()
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
    }

}

class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response =
            try {
                chain.proceed(chain.request())
            } catch (error: IOException) {
                //TODO add exceptions sealed class
                throw Exception("No internet connection")
            }
        if (!response.isSuccessful) {
            when (response.code) {
                HttpURLConnection.HTTP_UNAVAILABLE -> throw Exception("ServerException.ServiceUnavailable")
                HttpURLConnection.HTTP_NOT_FOUND -> throw Exception("ClientException.NotFound")
                HttpURLConnection.HTTP_CLIENT_TIMEOUT -> throw Exception("ClientException.RequestTimeout")
                else -> throw Exception(IllegalStateException("The status code ${response.code} was received but not handled!")
                )
            }
        }
        return response
    }
}