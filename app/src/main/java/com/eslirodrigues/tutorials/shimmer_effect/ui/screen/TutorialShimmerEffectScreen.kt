package com.eslirodrigues.tutorials.shimmer_effect.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.tutorials.shimmer_effect.ui.components.TutorialShimmerEffect

@Composable
fun TutorialShimmerEffectScreen() {
    var isLoading by remember { mutableStateOf(true) }
    Column {
        Button(onClick = { isLoading = !isLoading }) { Text(text = "Loading") }
        LazyColumn {
            items(20) {
                if (!isLoading) {
                    Card(Modifier.border(2.dp, Color.Black)) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = "Image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(end = 10.dp)
                            )
                            Column(Modifier.padding(top = 15.dp)) {
                                Text(text = "Shimmer Title", fontWeight = ExtraBold, fontSize = 22.sp)
                                Text(text = "Description")
                            }
                        }
                    }
                } else TutorialShimmerEffect()
            }
        }
    }
}
