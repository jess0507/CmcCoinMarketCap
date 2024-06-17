package com.jess.coinmarketcapapiapp.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jess.coinmarketcapapiapp.data.remote.dto.Currency
import com.jess.coinmarketcapapiapp.data.remote.dto.CurrentQuote

@Composable
fun Item(
    currency: Currency,
    modifier: Modifier = Modifier,
    onClickItem: (() -> Unit)? = null
) {
    Row(
        modifier = modifier.clickable {
            onClickItem?.invoke()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currency.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = currency.symbol,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "${"%.2f".format(currency.quote.values.firstOrNull()?.price ?: 0)}",
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun ItemPreview() {
    val currency = Currency(
        id = 1,
        name = "Bitcoin",
        symbol = "BTC",
        quote = mapOf("USD" to CurrentQuote(price = 10.0))
    )
    Item(currency = currency)
}