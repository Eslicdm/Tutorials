package com.eslirodrigues.tutorials.retrofit_api.data.api

import com.eslirodrigues.tutorials.retrofit_api.data.model.dto.RetrofitApiProductDTO
import com.eslirodrigues.tutorials.retrofit_api.data.model.dto.RetrofitApiProductPost
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitApiProductService {
    @POST("catalog/product")
    suspend fun createProduct(
        @Body product: RetrofitApiProductPost
    ): RetrofitApiProductDTO

    @DELETE("catalog/product/{id}")
    suspend fun deleteProductById(
        @Path("id") id: String
    ): Response<ResponseBody>
}