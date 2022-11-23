package com.eslirodrigues.tutorials.retrofit_api.data.model.dto

import com.squareup.moshi.Json
import androidx.annotation.Keep
import com.eslirodrigues.tutorials.retrofit_api.data.model.RetrofitApiProduct

@Keep
data class RetrofitApiProductDTO(
    @Json(name = "category")
    val category: String = "",
    @Json(name = "created")
    val created: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "manufacturer")
    val manufacturer: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "price")
    val price: Int = 0,
    @Json(name = "status")
    val status: String = "",
    @Json(name = "tags")
    val tags: String = ""
) {
    fun toRetrofitApiProduct() = RetrofitApiProduct(
        name = this.name,
        id = this.id.toString()
    )
}