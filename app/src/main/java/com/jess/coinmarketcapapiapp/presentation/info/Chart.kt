package com.jess.coinmarketcapapiapp.presentation.info

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import com.jess.coinmarketcapapiapp.data.remote.dto.Quote
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import kotlin.math.ceil
import kotlin.math.roundToInt


@Composable
fun Chart(
    quotes: List<Quote>,
    modifier: Modifier = Modifier,
    graphColor: Color = Color.Green
) {
    if (quotes.isEmpty()) return
    val upperValue: Int = remember(quotes) {
        quotes.maxOfOrNull { it.quote.values.first().close }?.let { ceil(it).roundToInt() } ?: 1
    }
    Log.d("Chart", "maxQuotes=${quotes.maxOfOrNull { it.quote.values.first().close }}")

    val lowerValue: Int = remember(quotes) {
        quotes.minOfOrNull { it.quote.values.first().close }?.roundToInt() ?: 0
    }
    Log.d("Chart", "upperValue=$upperValue, lowerValue=$lowerValue")

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
    Canvas(modifier = modifier.fillMaxSize()) {
        val verticalRange = upperValue - lowerValue
        val horizontalStep = (size.width - spacing/2) / quotes.size
        val verticalStep = size.height / verticalRange
        Log.d("Chart", "length=${quotes.size}, verticalRange=$verticalRange, horizontalStep=$horizontalStep, verticalStep=$verticalStep")

        val timestamp = quotes.firstOrNull()?.quote?.values?.firstOrNull()?.timestamp
        val time = timestamp?.timeStamp2Date()
        val timeStep = (24 - (time?.hour ?:0))/quotes.size

        quotes.indices.forEach { i ->
            drawContext.canvas.nativeCanvas.apply {
                val showTime =
                    time?.plusHours(timeStep*i.toLong())?.format(DateTimeFormatter.ofPattern("HH:mm"))
                Log.d("Chart", "$i: showTime=$showTime")
                drawText(
                    "$showTime",
                    spacing + i * horizontalStep,
                    size.height - 5,
                    textPaint
                )
            }
        }

        val priceStep = (upperValue - lowerValue) / 5f
        quotes.indices.forEach { i ->
            Log.d("Chart", "i=$i, price=$${lowerValue + priceStep * i}")
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "${lowerValue + priceStep * i}",
                    30f,
                    size.height - spacing - i * size.height / 5f,
                    textPaint
                )
            }
        }

        var lastX = 0f
        val strokePath = Path().apply {
            val height = size.height
            for (i in quotes.indices) {
                val info = quotes[i]
                val nextInfo = quotes.getOrNull(i + 1) ?: quotes.last()
                val leftRatio = (info.quote.values.first().close - lowerValue) / (upperValue - lowerValue)
                val rightRatio = (nextInfo.quote.values.first().close - lowerValue) / (upperValue - lowerValue)

                val x1 = spacing + i * horizontalStep
                val y1 = height - (leftRatio * height).toFloat()
                val x2 = spacing + (i + 1) * horizontalStep
                val y2 = height - (rightRatio * height).toFloat()
                if (i == 0) {
                    moveTo(x1, y1)
                }
                lastX = (x1 + x2) / 2f
                quadraticTo(
                    x1, y1, lastX, (y1 + y2) / 2f
                )
            }
        }
        val fillPath = strokePath
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing.toFloat(), size.height - spacing)
                close()
            }
        drawPath(
            path = fillPath,
            brush = Brush.verticalGradient(
                colors = listOf(
                    transparentGraphColor,
                    Color.Transparent
                ),
                endY = size.height - spacing
            )
        )
    }
}


fun String.timeStamp2Date(): ZonedDateTime? {
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    try {
        val date = ZonedDateTime.parse(this, formatter)
        return date
    } catch (e: DateTimeParseException) {
        println("Error parsing date: " + e.message)
        return null
    }
}