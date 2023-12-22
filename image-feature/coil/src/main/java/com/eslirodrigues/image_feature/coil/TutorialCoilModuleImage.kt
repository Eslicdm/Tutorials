package com.eslirodrigues.image_feature.coil

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

// App(ImageFunctionality)
// Mod:image-feature:coil(returns an image)

// App(AgeFunctionality(getAgeFromApi), NameFunctionality(getNameFromApi))
// Mod:Api-Call:Ktor:getAgeUsingApi(), getWeightUsingApi()
// Mod:Age-Feature:AgeValidation(age) - Mod:Name-Feature:NameValidation(name)

// App(AgeFunctionality)
// Mod:Age-Feature:data(Ktor)/domain(Validate)/presentation(Compose) - Mod:Age-Feature:Data(:Ktor-:Validation)

// Common usages: UI Elements, Logs, Network, Utilities

@Composable
fun TutorialCoilModuleImage() {
    val url = "https://cdn.pixabay.com/photo/2016/08/24/14/29/earth-1617121_960_720.jpg"
    SubcomposeAsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = url,
        contentDescription = "Earth"
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
            is AsyncImagePainter.State.Error -> {
                SubcomposeAsyncImageContent(painter = painterResource(id = R.drawable.ic_broken_image),)
            }
            is AsyncImagePainter.State.Success -> { SubcomposeAsyncImageContent() }
            else -> SubcomposeAsyncImageContent(painter = painterResource(id = R.drawable.ic_image),)
        }
    }
}