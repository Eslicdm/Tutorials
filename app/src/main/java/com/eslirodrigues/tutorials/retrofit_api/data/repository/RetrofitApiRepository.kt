package com.eslirodrigues.tutorials.retrofit_api.data.repository

import com.eslirodrigues.tutorials.retrofit_api.data.RetrofitApiResult
import com.eslirodrigues.tutorials.retrofit_api.data.model.RetrofitApiItem
import com.eslirodrigues.tutorials.retrofit_api.data.model.RetrofitApiProduct
import com.eslirodrigues.tutorials.retrofit_api.data.model.dto.RetrofitApiProductPost
import kotlinx.coroutines.flow.Flow

interface RetrofitApiRepository {
    suspend fun getItemsFromApi(): Flow<RetrofitApiResult<RetrofitApiItem>>
    suspend fun createProduct(productPost: RetrofitApiProductPost): Flow<RetrofitApiResult<RetrofitApiProduct>>
    suspend fun deleteProductById(id: String)
}