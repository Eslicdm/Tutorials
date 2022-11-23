package com.eslirodrigues.tutorials.retrofit_api.data.api

import com.eslirodrigues.tutorials.BuildConfig
import com.eslirodrigues.tutorials.retrofit_api.data.model.dto.RetrofitApiItemDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApiItemService {
    @Headers(
        "X-RapidAPI-Key: ${BuildConfig.API_KEY}",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    @GET("{category}/list") // "tips/list?id=3562&from=0&size=2"
    suspend fun getItemsFromApi(
        @Path("category") category: String,
        @Query("id") id: String = "3562",
        @Query("from") from: String,
        @Query("size") size: String
    ) : RetrofitApiItemDTO
}