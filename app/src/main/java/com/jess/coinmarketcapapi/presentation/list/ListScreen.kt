package com.jess.coinmarketcapapi.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jess.coinmarketcapapi.data.remote.dto.Currency
import com.jess.coinmarketcapapi.data.remote.dto.CurrentQuote
import com.jess.coinmarketcapapi.presentation.navigation.Screen
import com.jess.coinmarketcapapi.ui.theme.DarkBlue

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state
    ListScreen(
        items = state,
        onClickItem = {
            navController?.navigate(Screen.Info.name)
        },
    )
}

@Composable
internal fun ListScreen(
    modifier: Modifier = Modifier,
    items: List<Currency>,
    onClickItem: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlue)
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(items.size) { i ->
                val currency = items[i]
                Item(
                    currency = currency,
                    onClickItem = onClickItem,
                )
                if (i < items.size) {
                    Divider()
                }
            }
        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    val items = listOf(
        Currency(
            id = 1,
            name = "BitCoin",
            symbol = "BTC",
            quote = mapOf("BTC" to CurrentQuote(price = 10.0001))
        )
    )
    ListScreen(modifier = Modifier, items = items)
}