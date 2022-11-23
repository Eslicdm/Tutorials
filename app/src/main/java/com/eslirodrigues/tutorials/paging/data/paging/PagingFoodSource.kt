package com.eslirodrigues.tutorials.paging.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eslirodrigues.tutorials.paging.data.api.PagingFoodApi
import com.eslirodrigues.tutorials.paging.data.model.PagingFood
import javax.inject.Inject

class PagingFoodSource @Inject constructor(
    private val foodApi: PagingFoodApi
): PagingSource<Int, PagingFood>() {

    override fun getRefreshKey(state: PagingState<Int, PagingFood>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingFood> {
        return try {
            val food = foodApi.getAllPizzas().toPagingFood("Pizza")
            LoadResult.Page(
                data = listOf(food),
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}