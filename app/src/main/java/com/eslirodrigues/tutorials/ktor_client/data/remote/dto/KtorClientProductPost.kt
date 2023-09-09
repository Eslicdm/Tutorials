package com.eslirodrigues.tutorials.ktor_client.data.remote.dto

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class KtorClientProductPost(
    @SerialName("category")
    val category: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("manufacturer")
    val manufacturer: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("price")
    val price: Int = 0,
    @SerialName("tags")
    val tags: String = ""
)