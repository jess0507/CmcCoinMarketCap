package com.jess.coinmarketcapapi.data.remote.dto

data class CurrencyListResponse(
    var `data`: List<Currency> = emptyList(),
    var status: Status = Status()
)

data class Currency(
    var id: Int = 0,
    var name: String = "",
    var symbol: String = "",
    var tags: List<String> = emptyList(),
    var quote: Map<String, CurrentQuote> = mapOf(),
    var circulating_supply: Double? = null,
    var cmc_rank: Int? = null,
    var date_added: String? = null,
    var infinite_supply: Boolean? = null,
    var last_updated: String? = null,
    var max_supply: Long? = null,
    var num_market_pairs: Int? = null,
    var platform: Platform? = null,
    var self_reported_circulating_supply: Double? = null,
    var self_reported_market_cap: Double? = null,
    var slug: String? = null,
    var total_supply: Double? = null,
    var tvl_ratio: Double? = null
)

data class Platform(
    var id: Int? = null,
    var name: String? = null,
    var slug: String? = null,
    var symbol: String? = null,
    var token_address: String? = null
)

data class CurrentQuote(
    var price: Double = 0.0,
    var fully_diluted_market_cap: Double? = null,
    var last_updated: String? = null,
    var market_cap: Double? = null,
    var market_cap_dominance: Double? = null,
    var percent_change_1h: Double? = null,
    var percent_change_24h: Double? = null,
    var percent_change_30d: Double? = null,
    var percent_change_60d: Double? = null,
    var percent_change_7d: Double? = null,
    var percent_change_90d: Double? = null,
    var tvl: Double? = null,
    var volume_24h: Double? = null,
    var volume_change_24h: Double? = null
)