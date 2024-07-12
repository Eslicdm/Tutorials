package com.eslirodrigues.tutorials.preview.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.eslirodrigues.tutorials.preview.ui.preview.TutorialFoldablePreview
import com.eslirodrigues.tutorials.preview.ui.preview.TutorialPhonePreview
import com.eslirodrigues.tutorials.preview.ui.preview.TutorialPreviewParameterProvider
import com.eslirodrigues.tutorials.preview.ui.preview.TutorialTabletPreview
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme

@Composable
fun TutorialPreviewScreen(stateValues: Int = 0) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.error),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = when (stateValues) {
                    0 -> "First State"
                    1 -> "Second State"
                    else -> "Else State"
                },
                color = Color.Blue // Ui Check Mode Error
            )
        }
    }
}

@TutorialPhonePreview
@TutorialTabletPreview
@TutorialFoldablePreview
@Composable
fun TutorialPreviewScreenPreview(
    @PreviewParameter(TutorialPreviewParameterProvider::class ) stateValue: Int
) {
    TutorialsTheme {
        TutorialPreviewScreen(stateValue)
    }
}