package com.eslirodrigues.tutorials.paging.data.api

import com.eslirodrigues.tutorials.paging.data.model.dto.PagingFoodDTO
import retrofit2.http.GET

interface PagingFoodApi {

    @GET("api/images/pizza")
    suspend fun getAllPizzas(): PagingFoodDTO
}