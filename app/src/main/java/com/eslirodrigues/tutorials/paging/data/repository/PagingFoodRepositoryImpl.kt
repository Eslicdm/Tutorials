package com.eslirodrigues.tutorials.paging.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.eslirodrigues.tutorials.paging.data.api.PagingFoodApi
import com.eslirodrigues.tutorials.paging.data.model.PagingFood
import com.eslirodrigues.tutorials.paging.data.paging.PagingFoodSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingFoodRepositoryImpl @Inject constructor(
    private val foodApi: PagingFoodApi
): PagingFoodRepository {

    override fun getAllPizzas(): Flow<PagingData<PagingFood>> = Pager(
        config = PagingConfig(pageSize = 15, maxSize = 100),
        pagingSourceFactory = { PagingFoodSource(foodApi) }
    ).flow
}