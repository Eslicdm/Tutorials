package com.eslirodrigues.tutorials.pull_refresh.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eslirodrigues.tutorials.pull_refresh.viewmodel.PullRefreshViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshScreen(
    pullRefreshViewModel: PullRefreshViewModel = viewModel(),
) {
    val list = pullRefreshViewModel.list

    val pullRefreshState = rememberPullRefreshState(
        refreshing = pullRefreshViewModel.isRefreshing,
        onRefresh = { pullRefreshViewModel.getSwipeList() }
    )

    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(list) { number ->
                Text(number.toString(), fontSize = 36.sp)
            }
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = pullRefreshViewModel.isRefreshing,
            state = pullRefreshState
        )
    }
}