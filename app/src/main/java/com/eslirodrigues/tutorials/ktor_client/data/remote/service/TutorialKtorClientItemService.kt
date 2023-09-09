package com.eslirodrigues.tutorials.ktor_client.data.remote.service

import com.eslirodrigues.tutorials.BuildConfig
import com.eslirodrigues.tutorials.ktor_client.data.remote.dto.KtorClientItemDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.path
import javax.inject.Inject
import javax.inject.Named

class TutorialKtorClientItemService @Inject constructor(
    @Named("item") private val httpClient: HttpClient
) {
    suspend fun getItemsFromApi(
        category: String,
        id: String = "3562",
        from: String = "0",
        size: String = "2"
    ): KtorClientItemDTO {
        // "tips/list?id=3562&from=0&size=2"
        return httpClient.use { client ->
            client.get("https://tasty.p.rapidapi.com/") {
                header("X-RapidAPI-Key", BuildConfig.API_KEY)
                header("X-RapidAPI-Host", "tasty.p.rapidapi.com")
                url { path(category, "list") }
                parameter("id", id)
                parameter("from", from)
                parameter("size", size)
            }.body()
        }
    }
}