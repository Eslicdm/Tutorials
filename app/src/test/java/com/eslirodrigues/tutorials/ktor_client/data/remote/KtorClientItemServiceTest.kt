package com.eslirodrigues.tutorials.ktor_client.data.remote

import com.eslirodrigues.tutorials.ktor_client.data.remote.service.TutorialKtorClientItemService
import com.eslirodrigues.tutorials.retrofit_api.extension.readFile
import com.google.common.truth.Truth.assertThat
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test

class KtorClientItemServiceTest {
    // url - https://tasty.p.rapidapi.com/tips/list?id=3562&from=0&size=2

    private lateinit var ktorClientItemService: TutorialKtorClientItemService
    private val responseFile = "ktor_client/getItemsApiResponseFile.json".readFile()
    private val mockEngine = MockEngine { request ->
        when (request.url.fullPath) {
            "/tips/list?id=3562&from=0&size=2" -> {
                respond(
                    content = responseFile,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            else -> error("Error ${request.url.fullPath}")
        }
    }

    @Before
    fun setup() {
        val httpClientMock = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
        ktorClientItemService = TutorialKtorClientItemService(httpClientMock)
    }

    @After fun close() { mockEngine.close() }

    @Test
    fun makeRequest_Assert_IsPathRight() = runTest {
        ktorClientItemService.getItemsFromApi("tips", "3562", "0", "2")
        val requestPath = mockEngine.requestHistory.first().url.fullPath
        assertThat(requestPath).isEqualTo("/tips/list?id=3562&from=0&size=2")
    }

    @Test
    fun getItems_Assert_AuthorNameIsNotEmpty() = runTest {
        val result = ktorClientItemService.getItemsFromApi("tips")
        val authorName = result.results.first().authorName
        assertThat(authorName).isNotEmpty()
    }
}