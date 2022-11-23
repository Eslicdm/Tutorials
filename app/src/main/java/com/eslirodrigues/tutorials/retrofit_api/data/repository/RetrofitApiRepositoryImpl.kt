package com.eslirodrigues.tutorials.retrofit_api.data.repository

import android.content.Context
import android.widget.Toast
import com.eslirodrigues.tutorials.retrofit_api.data.RetrofitApiResult
import com.eslirodrigues.tutorials.retrofit_api.data.api.RetrofitApiItemService
import com.eslirodrigues.tutorials.retrofit_api.data.api.RetrofitApiProductService
import com.eslirodrigues.tutorials.retrofit_api.data.model.RetrofitApiItem
import com.eslirodrigues.tutorials.retrofit_api.data.model.RetrofitApiProduct
import com.eslirodrigues.tutorials.retrofit_api.data.model.dto.RetrofitApiProductPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitApiRepositoryImpl @Inject constructor(
    private val retrofitApiItem: RetrofitApiItemService,
    private val retrofitApiProduct: RetrofitApiProductService,
    private val context: Context
) : RetrofitApiRepository {

    override suspend fun getItemsFromApi(): Flow<RetrofitApiResult<RetrofitApiItem>> = flow {
        emit(RetrofitApiResult.Loading)
        try {
            val item = retrofitApiItem.getItemsFromApi(
                category = "tips",
                from = "0",
                size = "2"
            ).toRetrofitApiItem()
            emit(RetrofitApiResult.Success(item))
        } catch (e: Exception) {
            emit(RetrofitApiResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createProduct(
        productPost: RetrofitApiProductPost
    ): Flow<RetrofitApiResult<RetrofitApiProduct>> = flow {
        emit(RetrofitApiResult.Loading)
        try {
            val product = retrofitApiProduct.createProduct(productPost).toRetrofitApiProduct()
            emit(RetrofitApiResult.Success(product))
        } catch (e: Exception) {
            emit(RetrofitApiResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteProductById(id: String) {
        withContext(Dispatchers.Main) {
            val response = retrofitApiProduct.deleteProductById(id)
            if (response.isSuccessful) {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}