package com.eslirodrigues.tutorials.utils

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.tutorials.R

@Composable
fun TutorialCanvas() {
    val textMeasurer = rememberTextMeasurer()
    val textToDraw by remember { mutableStateOf("Text") }

    val imageToDraw = ImageBitmap.imageResource(R.drawable.sky)

    val customRect = ShapeDrawable(RectShape())

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(color = Color.Black, size = size)
            drawImage(
                image = imageToDraw,
                topLeft = Offset(250f, 1050f),
            )
            drawRoundRect(
                color = Color.Blue,
                topLeft = Offset(100f, 200f),
                cornerRadius = CornerRadius(100f, 100f),
                size = DpSize(100.dp, 230.dp).toSize()
            )
            drawOval(
                color = Color.Yellow,
                topLeft = Offset(400f, 200f),
                size = Size(200f, 200f)
            )
            drawPoints(
                color = Color.White,
                points = listOf(Offset(500F, 600F), Offset(550F, 650F)),
                pointMode = PointMode.Points,
                strokeWidth = 30f,
                cap = StrokeCap.Round
            )
            drawPoints(
                color = Color.White,
                points = listOf(Offset(700F, 600F), Offset(750F, 650F)),
                pointMode = PointMode.Polygon,
                strokeWidth = 30f,
                cap = StrokeCap.Square
            )
            drawArc(
                color = Color.Green,
                startAngle = 0f,
                useCenter = false,
                sweepAngle = 100f,
                size = Size(300f, 300f),
                topLeft = Offset(100f, 700f)
            )
            drawArc(
                color = Color.Green,
                startAngle = 0f,
                useCenter = true,
                sweepAngle = 100f,
                size = Size(300f, 300f),
                topLeft = Offset(350f, 700f)
            )
            withTransform({
                inset(horizontal = 200f, vertical = 800f)
                scale(scaleX = 1.4f, scaleY = 1f)
                translate(left = 0.5f, top = 1000f)
                rotate(degrees = 30F)
            }) {
                drawRect(color = Color.Gray, size = Size(300f, 300f))
            }
        }
        Box(
            modifier = Modifier
                .drawWithContent {
                    drawIntoCanvas { canvas ->
                        customRect.paint.color = Color.White.hashCode()
                        customRect.setBounds(0, 0, size.width.toInt(),size.height.toInt())
                        customRect.draw(canvas.nativeCanvas)
                    }
                }
                .size(50.dp)
        )
        Box(
            modifier = Modifier
                .drawWithCache {
                    val textLayout =
                        textMeasurer.measure(
                            text = AnnotatedString(textToDraw),
                            constraints = Constraints.fixed(500, 500),
                            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                            overflow = TextOverflow.Ellipsis,
                        )

                    val linePath = Path().apply {
                        moveTo(0f, 100f)
                        lineTo(size.width, 0f)
                        lineTo(size.width / 2f, size.height)
                        close()
                    }
                    onDrawBehind {
                        drawText(textLayoutResult = textLayout, color = Color.Magenta)
                        drawPath(path = linePath, color = Color.Black, style = Stroke(5f))
                    }
                }
                .size(50.dp)
        )
    }
}
