package com.eslirodrigues.tutorials.lazy_row_column_grid.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.eslirodrigues.tutorials.lazy_row_column_grid.ui.screen.TestTagsConstants.LAZY_COLUMN
import kotlinx.coroutines.launch

@Composable
fun TutorialLazyScreen() {
    val animalList = getAnimalLazyList()

    TutorialLazyColumn(animalList)
}

@Composable
fun TutorialLazyColumn(animalList: List<Pair<String, Int>>) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val showScrollToTopButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().testTag(LAZY_COLUMN),
        contentPadding = PaddingValues(40.dp),
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        items(
            items = animalList,
            key = { item -> item.second },
        ) { item ->
            TutorialLazyListItem(
                name = item.first,
                number = item.second
            )
        }
    }
    AnimatedVisibility(
        visible = showScrollToTopButton,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            IconButton(onClick = {
                scope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            }) {
                Icon(
                    modifier = Modifier
                        .size(300.dp)
                        .background(Color.Red),
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "scroll to top",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun TutorialLazyRow(animalList: List<Pair<String, Int>>) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val listMiddleItem = animalList.size / 2

    scope.launch {
        lazyListState.scrollToItem(listMiddleItem)
    }

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        itemsIndexed(animalList) { index, item ->
            if(lazyListState.isScrollInProgress)
                Text(text = index.toString())
            TutorialLazyListItem(
                name = item.first,
                number = item.second
            )
        }
    }
}

@Composable
fun TutorialLazyHorizontalGrid(
    animalList: List<Pair<String, Int>>,
) {
    LazyHorizontalGrid(
        modifier = Modifier.fillMaxSize(),
        rows = GridCells.Fixed(2),
        reverseLayout = true,
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(animalList) { item ->
            TutorialLazyListItem(
                name = item.first,
                number = item.second
            )
        }
    }
}

@Composable
fun TutorialLazyVerticalGrid(
    animalList: List<Pair<String, Int>>,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(1000.dp), // GridCells.Fixed(2)
    ) {
        items(items = animalList) { item ->
            TutorialLazyListItem(
                name = item.first,
                number = item.second
            )
        }
    }
}

fun getAnimalLazyList() : List<Pair<String, Int>> {
    val list = mutableListOf<Pair<String, Int>>()
    repeat(20) { number ->
        list.add("Cat" to number)
    }
    return list
}