package com.eslirodrigues.tutorials.utils.top_appbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScreen() {
    val exitUntilCollapsedScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val enterScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val pinnedScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(enterScrollBehavior.nestedScrollConnection),
        topBar = {
            TutorialTopAppBar(enterScrollBehavior)
//            TutorialTopAppBar(pinnedScrollBehavior)
//            TutorialTopAppBar(exitUntilCollapsedScrollBehavior)
//            TutorialMediumTopAppBar(enterScrollBehavior)
//            TutorialMediumTopAppBar(exitUntilCollapsedScrollBehavior)
//            TutorialLargeTopAppBar(enterScrollBehavior)
//            TutorialCenterAlignedTopAppBar(enterScrollBehavior)
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(50) {
                Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry")
            }
        }
    }
}