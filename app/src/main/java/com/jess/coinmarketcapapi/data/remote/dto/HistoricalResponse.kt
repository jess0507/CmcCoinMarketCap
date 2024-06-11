package com.jess.coinmarketcapapi.data.remote.dto

data class HistoricalResponse(
    val `data`: Map<String, CurrencyHistoricalData>,
    val status: Status
)
data class CurrencyHistoricalData(
    val id: Int,
    val name: String,
    val quotes: List<Quote>,
    val symbol: String
)
data class Quote(
    val quote: Map<String, ConvertCurrency>,
    val time_close: String,
    val time_high: String,
    val time_low: String,
    val time_open: String
)
data class ConvertCurrency(
    val close: Double,
    val high: Double,
    val low: Double,
    val market_cap: Double,
    val `open`: Double,
    val timestamp: String,
    val volume: Double
)