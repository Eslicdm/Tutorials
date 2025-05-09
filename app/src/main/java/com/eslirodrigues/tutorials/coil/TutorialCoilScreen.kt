package com.eslirodrigues.tutorials.coil

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import com.eslirodrigues.tutorials.R

@Composable
fun TutorialCoilScreen() {
    val url = "https://cdn.pixabay.com/photo/2016/08/24/14/29/earth-1617121_960_720.jpg"
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "AsyncImage", fontSize = 22.sp)
        TutorialCoilAsyncImage(url = url)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(text = "SubComposeAsyncImage Rounded, Error, Placeholder and Slow CrossFade", fontSize = 22.sp)
        TutorialCoilRoundedImageBuilderWithErrorAndSlowCrossFade(url = url, context = context)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(text = "SubComposeAsyncImage Loading, CachePolicy", fontSize = 22.sp)
        TutorialCoilLoadingCachePolicy(url = url, context = context)

        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text(text = "SubComposeAsyncImage with Content Scope and painter.state", fontSize = 22.sp)
        TutorialCoilSubComposeAsyncImageWithContentScope(url = url)
    }
}

@Composable
fun TutorialCoilAsyncImage(url: String) {
    AsyncImage(
        model = url,
        alpha = 0.5f,
        contentScale = ContentScale.Fit,
        contentDescription = "Earth"
    )
}

@Composable
fun TutorialCoilRoundedImageBuilderWithErrorAndSlowCrossFade(url: String, context: Context) {
    SubcomposeAsyncImage(
        modifier = Modifier.clip(CircleShape.copy(CornerSize(100.dp))),
        model = ImageRequest.Builder(context)
            .data(url)
            .crossfade(5000)
            .error(R.drawable.ic_broken_image)
            .placeholder(R.drawable.ic_image)
            .scale(Scale.FILL)
            .size(300)
            .build(),
        contentDescription = "Earth"
    )
}

@Composable
fun TutorialCoilLoadingCachePolicy(url: String, context: Context) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .diskCachePolicy(CachePolicy.DISABLED)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        error = {
            Icon(
                painter = painterResource(id = R.drawable.ic_broken_image),
                contentDescription = "Error")
        },
        contentDescription = "Earth"
    )
}


@Composable
fun TutorialCoilSubComposeAsyncImageWithContentScope(url: String) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = "Earth"
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> { CircularProgressIndicator() }
            is AsyncImagePainter.State.Error -> {
                SubcomposeAsyncImageContent(
                    painter = painterResource(id = R.drawable.ic_broken_image)
                )
            }
            is AsyncImagePainter.State.Success -> {
                SubcomposeAsyncImageContent(colorFilter = ColorFilter.tint(Color.Blue))
            }
            else -> {
                SubcomposeAsyncImageContent(
                    painter = painterResource(id = R.drawable.ic_image)
                )
            }
        }
    }
}