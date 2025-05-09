package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TutorialHorizontalPagerOnBoardingScreen() {
    TutorialHorizontalPager()
//    TutorialVerticalPager()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialHorizontalPager() {
    val pageItems = listOf(Color.Blue, Color.Green, Color.Red)
    val horizontalPagerState = rememberPagerState { pageItems.size }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = horizontalPagerState) { currentPage ->
            when (currentPage) {
                0 -> Box(modifier = Modifier.fillMaxSize().background(pageItems[0]))
                1 -> Box(modifier = Modifier.fillMaxSize().background(pageItems[1]))
                2 -> Box(modifier = Modifier.fillMaxSize().background(pageItems[2]))
            }
        }

        Row(modifier = Modifier.padding(50.dp).align(Alignment.BottomCenter)) {
            pageItems.forEachIndexed { index, color ->
                Canvas(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                        .clickable { scope.launch { horizontalPagerState.scrollToPage(index) } },
                    onDraw = {
                        drawCircle(color = Color.White, radius = this.size.minDimension / 2.0f)
                        drawCircle(color = color, radius = this.size.minDimension / 3.0f)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialVerticalPager() {
    val pageItems = listOf(Color.Blue, Color.Green, Color.Red)
    val verticalPagerState = rememberPagerState { pageItems.size }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = verticalPagerState) { currentPage ->
            when (currentPage) {
                0 -> Box(modifier = Modifier.fillMaxSize().background(pageItems[0]))
                1 -> Box(modifier = Modifier.fillMaxSize().background(pageItems[1]))
                2 -> Box(modifier = Modifier.fillMaxSize().background(pageItems[2]))
            }
        }

        Row(modifier = Modifier.padding(50.dp).align(Alignment.BottomCenter)) {
            pageItems.forEachIndexed { index, color ->
                Canvas(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                        .clickable { scope.launch { verticalPagerState.scrollToPage(index) } },
                    onDraw = {
                        drawCircle(color = Color.White, radius = this.size.minDimension / 2.0f)
                        drawCircle(color = color, radius = this.size.minDimension / 3.0f)
                    }
                )
            }
        }
    }
}