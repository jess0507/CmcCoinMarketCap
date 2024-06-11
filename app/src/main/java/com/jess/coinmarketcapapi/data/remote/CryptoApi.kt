package com.jess.coinmarketcapapi.data.remote

import com.jess.coinmarketcapapi.data.remote.dto.CurrencyListResponse
import com.jess.coinmarketcapapi.data.remote.dto.HistoricalResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CryptoApi {
    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCurrencyLastestList(
        @Header("X-CMC_PRO_API_KEY") apiKey: String = API_KEY
    ): CurrencyListResponse

    @GET("v1/cryptocurrency/ohlcv/historical")
    suspend fun getHistoricalData(
        @Header("X-CMC_PRO_API_KEY") apiKey: String = API_KEY,
        @Query("symbol") symbol: String,
        @Query("convert") convert: String,
        @Query("time_start") timeStart: Int,
        @Query("time_end") timeEnd: Int
    ): HistoricalResponse

    companion object {
        const val API_KEY = "fd98ab1c-f346-4631-aa1c-6e664907319f"
        const val BASE_URL = "https://pro-api.coinmarketcap.com"
        const val BASE_URL2 = "https://sandbox-api.coinmarketcap.com"
    }
}
