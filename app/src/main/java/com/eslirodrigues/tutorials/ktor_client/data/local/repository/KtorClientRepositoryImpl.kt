package com.eslirodrigues.tutorials.ktor_client.data.local.repository

import android.content.Context
import android.widget.Toast
import com.eslirodrigues.tutorials.ktor_client.data.TutorialKtorClientResult
import com.eslirodrigues.tutorials.ktor_client.data.local.model.KtorClientItem
import com.eslirodrigues.tutorials.ktor_client.data.local.model.KtorClientProduct
import com.eslirodrigues.tutorials.ktor_client.data.remote.dto.KtorClientProductPost
import com.eslirodrigues.tutorials.ktor_client.data.remote.service.TutorialKtorClientItemService
import com.eslirodrigues.tutorials.ktor_client.data.remote.service.TutorialKtorClientProductService
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KtorClientRepositoryImpl @Inject constructor(
    private val ktorClientItemService: TutorialKtorClientItemService,
    private val ktorClientProductService: TutorialKtorClientProductService,
    private val context: Context
): KtorClientRepository {
    override suspend fun getItemsFromApi(): Flow<TutorialKtorClientResult<KtorClientItem>> = flow {
        emit(TutorialKtorClientResult.Loading)
        try {
            val item = ktorClientItemService.getItemsFromApi(
                category = "tips"
            ).toKtorClientItem()
            emit(TutorialKtorClientResult.Success(item))
        } catch (e: Exception) {
            emit(TutorialKtorClientResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun createProduct(
        productPost: KtorClientProductPost
    ): Flow<TutorialKtorClientResult<KtorClientProduct>> = flow {
        emit(TutorialKtorClientResult.Loading)
        try {
            val product = ktorClientProductService.createProduct(productPost).toKtorClientProduct()
            emit(TutorialKtorClientResult.Success(product))
        } catch (e: Exception) {
            emit(TutorialKtorClientResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteProductById(id: String) {
        withContext(Dispatchers.Main) {
            val response = ktorClientProductService.deleteProductById(id)
            if (response.status.isSuccess()) {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}