package com.eslirodrigues.tutorials.paging.data.model.dto

import com.squareup.moshi.Json
import androidx.annotation.Keep
import com.eslirodrigues.tutorials.paging.data.model.PagingFood

@Keep
data class PagingFoodDTO(
    @Json(name = "image")
    val image: String = ""
) {
    fun toPagingFood(name: String) = PagingFood(
        name = name,
        image = this.image
    )
}