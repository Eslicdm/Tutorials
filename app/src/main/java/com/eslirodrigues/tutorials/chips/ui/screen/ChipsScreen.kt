package com.eslirodrigues.tutorials.chips.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.tutorials.chips.ui.components.TutorialAssistChip
import com.eslirodrigues.tutorials.chips.ui.components.TutorialFilterChip
import com.eslirodrigues.tutorials.chips.ui.components.TutorialInputChip
import com.eslirodrigues.tutorials.chips.ui.components.TutorialSuggestionChip

@Composable
fun ChipsScreen() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Assist Chip", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TutorialAssistChip()
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(text = "Filter Chip", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TutorialFilterChip()
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(text = "Input Chip", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TutorialInputChip()
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Text(text = "Suggestion Chip", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        TutorialSuggestionChip()
    }
}

