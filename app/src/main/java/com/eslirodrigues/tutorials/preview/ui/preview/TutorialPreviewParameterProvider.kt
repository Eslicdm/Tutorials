package com.eslirodrigues.tutorials.preview.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class TutorialPreviewParameterProvider : PreviewParameterProvider<Int> {
    override val values = sequenceOf(0, 1)
}