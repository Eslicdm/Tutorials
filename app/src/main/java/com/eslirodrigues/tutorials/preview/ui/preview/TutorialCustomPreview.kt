package com.eslirodrigues.tutorials.preview.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "Phone Portrait",
    group = "Phone",
    device = "spec: width=411dp, height=891dp",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Phone Landscape",
    group = "Phone",
    device = "spec: width=411dp, height=891dp, orientation=landscape",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class TutorialPhonePreview

@Preview(
    name = "Tablet Portrait",
    group = "Tablet",
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Tablet Landscape",
    group = "Tablet",
    device = "spec:width=1280dp,height=800dp,dpi=240",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class TutorialTabletPreview

@Preview(
    name = "Foldable Portrait",
    group = "Foldable",
    device = "spec:width=673dp,height=841dp",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Foldable Landscape",
    group = "Foldable",
    device = "spec:width=673dp,height=841dp,orientation=landscape",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class TutorialFoldablePreview