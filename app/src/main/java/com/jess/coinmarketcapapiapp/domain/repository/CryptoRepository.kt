package com.jess.coinmarketcapapiapp.domain.repository

import com.jess.coinmarketcapapiapp.data.remote.Resource
import com.jess.coinmarketcapapiapp.data.remote.dto.Currency
import com.jess.coinmarketcapapiapp.data.remote.dto.Quote
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun fetchLatestList(): Flow<Resource<List<Currency>>>
    suspend fun fetchHistoricalData(): Flow<Resource<List<Quote>>>
}