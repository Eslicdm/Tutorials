package com.eslirodrigues.tutorials.retrofit_api.data.model.dto

import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class RetrofitApiProductPost(
    @Json(name = "category")
    val category: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "manufacturer")
    val manufacturer: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "price")
    val price: Int = 0,
    @Json(name = "tags")
    val tags: String = ""
)