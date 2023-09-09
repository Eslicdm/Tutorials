package com.eslirodrigues.tutorials.ktor_client.data.remote

import com.eslirodrigues.tutorials.ktor_client.data.remote.dto.KtorClientProductPost
import com.eslirodrigues.tutorials.ktor_client.data.remote.service.TutorialKtorClientProductService
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
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.Before
import org.junit.Test

class KtorClientProductServiceTest {
    // 	https://onboarding-project5973.p.rapidapi.com/catalog/product - POST
    // 	https://onboarding-project5973.p.rapidapi.com/catalog/product/708643 - GET
    // 	https://onboarding-project5973.p.rapidapi.com/catalog/product/708643 - DELETE

    private lateinit var ktorClientProductService: TutorialKtorClientProductService
    private val createProductFile = "retrofit_api/createProductBodyFile.json".readFile()
    private val deleteProductFile = "retrofit_api/deleteProductFile.json".readFile()
    private val getProductFile = "retrofit_api/getProductFile.json".readFile()
    private val mockEngine = MockEngine { request ->
        when (request.url.fullPath) {
            "/catalog/product" -> {
                respond(
                    content = createProductFile,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            "/catalog/product/708643" -> {
                respond(
                    content = getProductFile,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            "/catalog/product/714786" -> {
                respond(
                    content = deleteProductFile,
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
        ktorClientProductService = TutorialKtorClientProductService(httpClientMock)
    }

    @After fun close() { mockEngine.close() }

    @Test
    fun createProduct_Assert_IsPathRight() = runTest {
        ktorClientProductService.createProduct(KtorClientProductPost())
        val requestPath = mockEngine.requestHistory.first().url.fullPath
        assertThat(requestPath).isEqualTo("/catalog/product")
    }

    @Test
    fun createProduct_Assert_IsNameRight() = runTest {
        val productName = ktorClientProductService
            .createProduct(KtorClientProductPost()).toKtorClientProduct().name
        assertThat(productName).isEqualTo("er")
    }

    @Test
    fun deleteProduct_Assert_IsProductDeleted() = runTest {
        val productName = ktorClientProductService.deleteProductById("714786")
        val status = productName.status.isSuccess()
        assertThat(status).isTrue()
    }
}