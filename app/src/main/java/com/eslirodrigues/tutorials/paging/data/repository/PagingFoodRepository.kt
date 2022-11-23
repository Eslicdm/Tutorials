package com.eslirodrigues.tutorials.paging.data.repository

import androidx.paging.PagingData
import com.eslirodrigues.tutorials.paging.data.model.PagingFood
import kotlinx.coroutines.flow.Flow

interface PagingFoodRepository {
    fun getAllPizzas(): Flow<PagingData<PagingFood>>
}