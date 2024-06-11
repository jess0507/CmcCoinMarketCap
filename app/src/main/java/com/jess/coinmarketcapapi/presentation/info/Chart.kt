package com.jess.coinmarketcapapi.presentation.info

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import com.jess.coinmarketcapapi.data.remote.dto.Quote
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import kotlin.math.round
import kotlin.math.roundToInt


@Composable
fun Chart(
    quotes: List<Quote>,
    modifier: Modifier = Modifier,
    graphColor: Color = Color.Green
) {
    Spacer(modifier = Modifier.statusBarsPadding())
    val upperValue: Int = remember(quotes) {
        quotes.maxOfOrNull { it.quote.values.first().close }?.plus(1)?.roundToInt() ?: 0
    }
    val lowerValue: Int = remember(quotes) {
        quotes.minOfOrNull { it.quote.values.first().close }?.roundToInt() ?: 0
    }
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }
    val spacing = 100L
    val transparentGraphColor = remember {
        graphColor.copy(0.5f)
    }
    Canvas(modifier = modifier) {
        val spacePerHour = (size.width - spacing) / quotes.size
        (0 until quotes.size - 1).forEach { i ->
//            val info = quotes[i]
//            val timestamp = info.quote.values.firstOrNull()?.timestamp
//            val hour = timestamp?.timeStamp2Date()?.hour
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "$i",
                    spacing + i * spacePerHour,
                    size.height - 5,
                    textPaint
                )
            }
        }
        val priceStep = (upperValue - lowerValue) / 5f
        (0..4).forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    round(lowerValue + priceStep * i).toString(),
                    30f,
                    size.height - spacing - i * size.height / 5f,
                    textPaint
                )
            }
        }
//        var lastX = 0f
//        val strokePath = android.graphics.Path().apply {
//            val height = size.height
//            for (i in quotes.indices) {
//                val info = quotes[i]
//                val nextInfo = quotes.getOrNull(i + 1) ?: quotes.last()
//                val leftRatio = (info.close - lowerValue) / (upperValue - lowerValue)
//                val rightRatio = (nextInfo.close - lowerValue) / (upperValue - lowerValue)
//
//                val x1 = spacing + i * spacePerHour
//                val y1 = height - spacing - (leftRatio * height).toFloat()
//                val x2 = spacing + (i + 1) * spacePerHour
//                val y2 = height - spacing - (rightRatio * height).toFloat()
//                if (i == 0) {
//                    moveTo(x1, y1)
//                }
//                lastX = (x1 + x2) / 2f
//                quadraticBezierTo(
//                    x1, y1, lastX, (y1 + y2) / 2f
//                )
//            }
//        }
//        val fillPath = android.graphics.Path(strokePath.asAndroidPath())
//            .asComposePath()
//            .apply {
//                lineTo(lastX, size.height - spacing)
//                lineTo(spacing, size.height - spacing)
//                close()
//            }
//        drawPath(
//            path = fillPath,
//            brush = Brush.verticalGradient(
//                colors = listOf(
//                    transparentGraphColor,
//                    Color.Transparent
//                ),
//                endY = size.height - spacing
//            )
//        )
//        drawPath(
//            path = strokePath,
//            color = graphColor,
//            style = Stroke(
//                width = 3.dp.toPx(),
//                cap = StrokeCap.Round
//            )
//        )
//    }
    }
}


fun String.timeStamp2Date(): ZonedDateTime? {
    val dateString = "2024-06-11T02:32:46.824Z"
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    try {
        val date = ZonedDateTime.parse(dateString, formatter)
        return date
    } catch (e: DateTimeParseException) {
        println("Error parsing date: " + e.message)
        return null
    }
}