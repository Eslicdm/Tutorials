package com.eslirodrigues.tutorials.animations.ui.screen

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.eslirodrigues.tutorials.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun TutorialAnimationsScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
//        TutorialAnimatedVisibility()
//        TutorialCrossFade()
//        TutorialAnimatedContent()
//        TutorialAnimatedContentSize()
//        TutorialAnimateItemPlacement()
//        TutorialAnimatedPosition()
//        TutorialRepeatableAnimation()
//        TutorialMultipleAnimations()
//        TutorialSequentialAndConcurrentAnimations()
//        TutorialShapeShifterAnimatedVectorDrawable()
        TutorialLottieAnimation()
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TutorialAnimatedVisibility() {
    var isVisible by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = isVisible,
        enter = expandIn() + fadeIn(),
        exit = shrinkOut() + fadeOut()
    ) {
        Box(modifier = Modifier
            .size(200.dp)
            .background(Red)
            .clickable {
                scope.launch {
                    isVisible = !isVisible
                    delay(500L)
                    isVisible = !isVisible
                }
            }
        ) {
            Box(modifier = Modifier
                    .size(100.dp)
                    .background(Green)
                    .align(Alignment.Center)
                    .animateEnterExit()
            )
        }
    }


    val visibleState = remember { MutableTransitionState(false).apply { targetState = true } }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = expandIn() + fadeIn(tween(1000)),
        exit = shrinkOut() + fadeOut(tween(1000))
    ) { Box(modifier = Modifier.size(200.dp).background(Red)) }

    TextButton(onClick = { visibleState.targetState = !visibleState.currentState }) {
        Text(
            text = when {
                visibleState.isIdle && visibleState.currentState -> "Visible"
                !visibleState.isIdle && visibleState.currentState -> "Disappearing"
                visibleState.isIdle && !visibleState.currentState -> "Invisible"
                else -> "Appearing"
            },
            fontSize = 28.sp
        )
    }
}

@Composable
fun TutorialCrossFade() {
    var targetState by remember { mutableIntStateOf(0) }
    Crossfade(
        targetState = targetState,
        animationSpec = tween(durationMillis = 1000),
        label = "Change Box"
    ) { targetInt ->
        when (targetInt) {
            0 -> Box(modifier = Modifier.size(50.dp).background(Red).clickable { targetState = 1 })
            1 -> Box(modifier = Modifier.size(50.dp).background(Blue).clickable { targetState = 0 })
        }
    }
}

@Composable
fun TutorialAnimatedContent() {
    var targetCountState by remember { mutableIntStateOf(0) }
    AnimatedContent(
        targetState = targetCountState,
        transitionSpec = { ((expandVertically()).togetherWith(shrinkVertically())) },
        label = "Increase Count"
    ) { targetInt ->
        TextButton(onClick = { targetCountState++ }) {
            Text(text = targetInt.toString(), fontSize = 28.sp)
        }
    }
}

@Composable
fun TutorialAnimatedContentSize() {
    var sizeValue by remember { mutableStateOf(200.dp) }
    val animatedSize by animateDpAsState(
        targetValue = sizeValue,
        animationSpec = tween(durationMillis = 2000),
        label = "Change Content Size"
    )
    Box(modifier = Modifier
        .size(animatedSize)
        .background(Blue)
        .clickable { sizeValue = if (sizeValue == 100.dp) 200.dp else 100.dp }
    )


    var isExpanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier
            .background(Red)
            .animateContentSize(tween(2000))
            .size(if (isExpanded) 200.dp else 100.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { isExpanded = !isExpanded }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialAnimateItemPlacement() {
    val listNumbers = remember {
        mutableStateListOf("Green" to Green, "Red" to Red, "Blue" to Blue, "Yellow" to Yellow)
    }
    LazyColumn {
        items(listNumbers, key = { it.first }) { color ->
            Box(modifier = Modifier
                .size(height = 100.dp, width = 400.dp)
                .background(color.second)
                .animateItemPlacement(tween(1000))
                .clickable { listNumbers.shuffle() }
            )
        }
    }
}

@Composable
fun TutorialAnimatedPosition() {
    var isMoved by remember { mutableStateOf(false) }
    val xOffset = with(LocalDensity.current) { -90.dp.toPx().roundToInt() }
    val yOffset = with(LocalDensity.current) { 20.dp.toPx().roundToInt() }
    val offset by animateIntOffsetAsState(
        targetValue = if (isMoved) IntOffset(xOffset, yOffset) else IntOffset.Zero,
        label = "change offset"
    )
    Box(modifier = Modifier
            .offset { offset }
            .size(100.dp)
            .background(Red)
            .clickable { isMoved = !isMoved }
    )


    var isBoxMoved by remember { mutableStateOf(false) }
    val offsetTarget = if (isBoxMoved) IntOffset(300, 0) else IntOffset.Zero
    val boxOffset by animateIntOffsetAsState(
        targetValue = offsetTarget,
        animationSpec = tween(50),
        label = "offset"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { isBoxMoved = !isBoxMoved }
    ) {
        Box(modifier = Modifier.size(100.dp).background(Red))
        Box(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val offsetValue = if (isLookingAhead) offsetTarget else boxOffset
                    val placeable = measurable.measure(constraints)
                    val height = if (isBoxMoved) offsetValue.y else placeable.height + offsetValue.y
                    layout(
                        width = placeable.width + offsetValue.x,
                        height = height
                    ) { placeable.placeRelative(offsetValue) }
                }
                .size(100.dp)
                .background(Green)
        )
        Box(modifier = Modifier.size(100.dp).background(Blue))
    }
}

@Composable
fun TutorialRepeatableAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val animatedColorInfinite by infiniteTransition.animateColor(
        initialValue = Red,
        targetValue = Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "infinite color"
    )
    Box(modifier = Modifier.size(200.dp).drawBehind { drawRect(animatedColorInfinite) })

    var startAnimation by remember { mutableStateOf(false) }
    val animatedColorFinite by animateColorAsState(
        targetValue = if (startAnimation) Green else Red,
        animationSpec = repeatable (
            iterations = 2,
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(1000)
        ),
        label = "finite color"
    )
    Box(modifier = Modifier
            .size(200.dp)
            .drawBehind { drawRect(animatedColorFinite) }
            .clickable { startAnimation = !startAnimation }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TutorialMultipleAnimations() {
    var isExpanded by remember { mutableStateOf(false) }
    val transition = updateTransition(isExpanded, label = "box state")
    val animatedSize by transition.animateDp(label = "box size") { expandedState ->
        if (expandedState) 200.dp else 100.dp
    }
    val animatedColor by transition.animateColor(label = "box color") { expandedState ->
        if (expandedState) Red else Green
    }
    Box(modifier = Modifier
        .background(animatedColor)
        .size(animatedSize)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) { isExpanded = !isExpanded }
    )

    transition.AnimatedVisibility(visible = { isExpanded }) {
        Box(modifier = Modifier.size(100.dp).background(Blue))
    }
    transition.AnimatedContent { expandedState ->
        Box(modifier = Modifier.size(100.dp).background(if (expandedState) Yellow else DarkGray))
    }
}

@Composable
fun TutorialSequentialAndConcurrentAnimations() {
    val sequentialColorAnimation = remember { Animatable(Green) }
    val sequentialAlphaAnimation = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        sequentialColorAnimation.animateTo(Red, tween(2000))
        sequentialAlphaAnimation.animateTo(0.1f, tween(2000))
    }
    Box(modifier = Modifier
        .size(200.dp)
        .drawBehind {
            drawRect(sequentialColorAnimation.value.copy(alpha = sequentialAlphaAnimation.value))
        }
    )


    val concurrentColorAnimation = remember { Animatable(Green) }
    val concurrentAlphaAnimation = remember { Animatable(1f) }
    LaunchedEffect(Unit) {
        launch { concurrentColorAnimation.animateTo(Red, tween(2000)) }
        launch { concurrentAlphaAnimation.animateTo(0.1f, tween(2000)) }

    }
    Box(modifier = Modifier
        .size(200.dp)
        .drawBehind {
            drawRect(concurrentColorAnimation.value.copy(alpha = concurrentAlphaAnimation.value))
        }
    )
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun TutorialShapeShifterAnimatedVectorDrawable() {
    val animatedVector = AnimatedImageVector.animatedVectorResource(R.drawable.ic_home_animated)
    var isAtEnd by remember { mutableStateOf(false) }
    Image(
        rememberAnimatedVectorPainter(animatedVector, isAtEnd),
        contentDescription = "Home",
        modifier = Modifier.clickable { isAtEnd = !isAtEnd },
    )
}

@Composable
fun TutorialLottieAnimation() {
    val compositionAnimation by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.dot_lottie_animation)
    )
    LottieAnimation(
        composition = compositionAnimation,
        iterations = LottieConstants.IterateForever,
    )
}












