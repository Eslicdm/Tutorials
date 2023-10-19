package com.eslirodrigues.tutorials.paging.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import com.eslirodrigues.tutorials.R
import com.eslirodrigues.tutorials.paging.data.model.PagingFood
import com.eslirodrigues.tutorials.paging.ui.viewmodel.PagingViewModel

@Composable
fun TutorialPagingScreen(
    viewModel: PagingViewModel = hiltViewModel()
) {
    val foodItems: LazyPagingItems<PagingFood> = viewModel.getAllPizzas().collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn {
            items(items = foodItems.itemSnapshotList) { item ->
                SubcomposeAsyncImage(
                    modifier = Modifier.size(300.dp),
                    contentScale = ContentScale.Crop,
                    loading = { CircularProgressIndicator() },
                    error = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_broken_image),
                            contentDescription = "error"
                        )
                    },
                    model = item?.image,
                    contentDescription = item?.name
                )
            }
        }
    }
}