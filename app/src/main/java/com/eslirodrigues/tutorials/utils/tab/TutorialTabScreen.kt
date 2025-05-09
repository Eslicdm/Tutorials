package com.eslirodrigues.tutorials.utils.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class TutorialTabModel(
    val title: String,
    val icon: ImageVector? = null,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialTabScreen() {
    val tabItems = listOf(
        TutorialTabModel("Image", Icons.Default.Image),
        TutorialTabModel("Music", Icons.Default.MusicNote),
        TutorialTabModel("Video", Icons.Default.Videocam),
    )

    val pagerState = rememberPagerState { tabItems.size }
    val scope = rememberCoroutineScope()

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabItems.forEachIndexed { index, item ->
                TutorialTabItem(
                    onClick = { scope.launch { pagerState.scrollToPage(index) } },
                    selected = pagerState.currentPage == index,
                    title = item.title,
                    icon = item.icon,
                )
            }
        }
        HorizontalPager(state = pagerState) { currentPage ->
            when(currentPage) {
                 0 -> { TutorialImageTab() }
                 1 -> { TutorialMusicTab() }
                 2 -> { TutorialVideoTab() }
            }
        }
    }
}

@Composable
fun TutorialTabItem(
    onClick: () -> Unit,
    selected: Boolean,
    title: String,
    icon: ImageVector? = null,
) {
    val tabHeight = if (icon != null) 54.dp else 38.dp
    val bottomPadding = if (icon != null) 5.dp else 5.dp
    val selectedColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    Tab(selected = selected, onClick = onClick) {
        Column(
            Modifier
                .padding(top = 10.dp, bottom = bottomPadding)
                .height(tabHeight)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (icon != null) Icon(icon, contentDescription = title, tint = selectedColor)
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = title,
                color = selectedColor
            )
        }
    }
}