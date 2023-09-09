package com.eslirodrigues.tutorials.ktor_client.data.remote.dto

import androidx.annotation.Keep
import com.eslirodrigues.tutorials.ktor_client.data.local.model.KtorClientProduct
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class KtorClientProductDTO(
    @SerialName("category")
    val category: String = "",
    @SerialName("created")
    val created: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("manufacturer")
    val manufacturer: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("price")
    val price: Int = 0,
    @SerialName("status")
    val status: String = "",
    @SerialName("tags")
    val tags: String = ""
) {
    fun toKtorClientProduct() = KtorClientProduct(
        name = this.name,
        id = this.id.toString()
    )
}