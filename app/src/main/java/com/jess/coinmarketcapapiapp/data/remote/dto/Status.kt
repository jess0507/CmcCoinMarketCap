package com.jess.coinmarketcapapiapp.data.remote.dto

data class Status(
    var credit_count: Int? = null,
    var elapsed: Int? = null,
    var error_code: Int? = null,
    var error_message: Any? = null,
    var notice: Any? = null,
    var timestamp: String? = null
)