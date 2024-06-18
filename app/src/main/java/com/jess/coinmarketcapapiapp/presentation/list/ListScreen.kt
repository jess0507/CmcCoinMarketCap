package com.jess.coinmarketcapapiapp.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jess.coinmarketcapapiapp.data.remote.dto.Currency
import com.jess.coinmarketcapapiapp.data.remote.dto.CurrentQuote
import com.jess.coinmarketcapapiapp.presentation.navigation.Screen
import com.jess.coinmarketcapapiapp.ui.theme.DarkBlue

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state
    ListScreen(
        isLoading = viewModel.isLoading,
        items = state,
        onClickItem = { symbol ->
            navController.navigate("${Screen.Info.name}/${symbol}")
        },
    )
}

@Composable
internal fun ListScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    items: List<Currency>,
    onClickItem: ((String?) -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (isLoading && items.isEmpty()) {
            CircularProgressIndicator()
        } else {
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
    ListScreen(modifier = Modifier, items = items, isLoading = true)
}