package com.eslirodrigues.tutorials.ktor_client.data.remote.service

import com.eslirodrigues.tutorials.ktor_client.data.remote.dto.KtorClientProductDTO
import com.eslirodrigues.tutorials.ktor_client.data.remote.dto.KtorClientProductPost
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import javax.inject.Inject
import javax.inject.Named

class TutorialKtorClientProductService @Inject constructor(
    @Named("product") private val httpClient: HttpClient
) {
//    "https://onboarding-project5973.p.rapidapi.com/catalog/product"
    suspend fun createProduct(product: KtorClientProductPost): KtorClientProductDTO {
       return httpClient.post {
            contentType(ContentType.Application.Json)
            url { path("catalog/product") }
            setBody(product)
        }.body()
    }
//    "https://onboarding-project5973.p.rapidapi.com/catalog/product/id"
    suspend fun deleteProductById(id: String): HttpResponse {
        return httpClient.delete {
            url { path("catalog/product/$id") }
        }
    }
}