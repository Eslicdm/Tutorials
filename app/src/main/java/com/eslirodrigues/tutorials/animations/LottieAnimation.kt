package com.eslirodrigues.tutorials.animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.eslirodrigues.tutorials.R

@Composable
fun LottieAnimation() {
    val compositionAnimation by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.dot_lottie_animation)
    )
    com.airbnb.lottie.compose.LottieAnimation(
        composition = compositionAnimation,
        iterations = LottieConstants.IterateForever,
    )
}