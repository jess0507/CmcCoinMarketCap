package com.jess.coinmarketcapapiapp.data.remote

import com.jess.coinmarketcapapiapp.data.remote.dto.CurrencyListResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CryptoApi {
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCurrencyLastestList(
        @Header("X-CMC_PRO_API_KEY") apiKey: String = API_KEY
    ): CurrencyListResponse

    companion object {
        const val API_KEY = "fd98ab1c-f346-4631-aa1c-6e664907319f"
        const val BASE_URL = "https://pro-api.coinmarketcap.com"
    }
}
