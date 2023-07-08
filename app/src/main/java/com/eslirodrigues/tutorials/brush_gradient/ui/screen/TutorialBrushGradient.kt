package com.eslirodrigues.tutorials.brush_gradient.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.tutorials.R

@Composable
fun TutorialBrushGradient() {
    val horizontalGradient = Brush.horizontalGradient(listOf(Color.Red, Color.DarkGray))
    val horizontalGradientRange = Brush.horizontalGradient(
        colorStops = arrayOf(
            0.2f to Color.Red,
            0.6f to Color.DarkGray
        )
    )
    val linearGradient = Brush.linearGradient(listOf(Color.Red, Color.DarkGray))
    val verticalGradient = Brush.verticalGradient(listOf(Color.Red, Color.DarkGray))
    val radialGradient = Brush.radialGradient(listOf(Color.Red, Color.DarkGray))

    val customRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val maxDimensions = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color.Red, Color.DarkGray),
                center = size.center,
                radius = maxDimensions / 1.25f,
                colorStops = listOf(0f, 1f)
            )
        }
    }
    val tileMirrorSize = with(LocalDensity.current) { 25.dp.toPx() }
    val horizontalGradientRepeated = Brush.horizontalGradient(
        colors = listOf(Color.Red, Color.DarkGray),
        endX = tileMirrorSize,
        tileMode = TileMode.Repeated
    )
    val customLinearGradientMirror = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                return LinearGradientShader(
                    colors = listOf(Color.Red, Color.DarkGray),
                    from = Offset.Zero,
                    to = Offset(size.width / 20f, 0f),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }
    val image = ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.sky)))

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
//        Canvas(modifier = Modifier.size(120.dp)) { drawRect(horizontalGradient) }
//        Canvas(modifier = Modifier.size(120.dp)) { drawRect(horizontalGradientRange) }
//        Canvas(modifier = Modifier.size(120.dp)) { drawRect(linearGradient) }
//        Canvas(modifier = Modifier.size(120.dp)) { drawRect(verticalGradient) }
        Canvas(modifier = Modifier.size(120.dp)) { drawRect(radialGradient) }

        Canvas(modifier = Modifier.size(120.dp)) { drawRect(customRadialGradient) }
        Canvas(modifier = Modifier.size(120.dp)) { drawRect(horizontalGradientRepeated) }
        Canvas(modifier = Modifier.size(120.dp)) { drawRect(customLinearGradientMirror) }
        Text(
            text = "Image Text",
            fontSize = 70.sp,
            style = TextStyle(brush = image, fontWeight = FontWeight.ExtraBold)
        )
    }
}