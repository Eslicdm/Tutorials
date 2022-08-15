package com.eslirodrigues.tutorials.swipetorefresh_accompanist.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eslirodrigues.tutorials.swipetorefresh_accompanist.viewmodel.SwipeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SwipeAccompanistScreen(
    swipeViewModel: SwipeViewModel = viewModel()
) {
    val list = swipeViewModel.list

    SwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        state = rememberSwipeRefreshState(isRefreshing = false),
        onRefresh = { swipeViewModel.getSwipeList() }
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(top = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(list) { number ->
                Text(number.toString(), fontSize = 36.sp)
            }
        }
    }
}