package com.eslirodrigues.tutorials.ktor_client.data.local.repository

import com.eslirodrigues.tutorials.ktor_client.data.TutorialKtorClientResult
import com.eslirodrigues.tutorials.ktor_client.data.local.model.KtorClientItem
import com.eslirodrigues.tutorials.ktor_client.data.local.model.KtorClientProduct
import com.eslirodrigues.tutorials.ktor_client.data.remote.dto.KtorClientProductPost
import kotlinx.coroutines.flow.Flow

interface KtorClientRepository {
    suspend fun getItemsFromApi(): Flow<TutorialKtorClientResult<KtorClientItem>>
    suspend fun createProduct(
        productPost: KtorClientProductPost
    ): Flow<TutorialKtorClientResult<KtorClientProduct>>
    suspend fun deleteProductById(id: String)
}