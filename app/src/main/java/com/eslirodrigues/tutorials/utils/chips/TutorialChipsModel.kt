package com.eslirodrigues.tutorials.utils.chips

import androidx.compose.ui.graphics.vector.ImageVector

data class TutorialChipsModel(
    val name: String,
    val subList: List<String>? = null,
    val textExpanded: String? = null,
    val leadingIcon: ImageVector? = null,
    val trailingIcon: ImageVector? = null,
)









