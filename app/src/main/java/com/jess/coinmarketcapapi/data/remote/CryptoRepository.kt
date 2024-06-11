package com.jess.coinmarketcapapi.data.remote

import com.jess.coinmarketcapapi.data.remote.dto.Currency
import com.jess.coinmarketcapapi.data.remote.dto.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoRepository @Inject constructor(private val api: CryptoApi) {

    suspend fun fetchLatestList(): Flow<Resource<List<Currency>>> {
        return flow {
            emit(Resource.Loading())
            val response = try {
                api.getCurrencyLastestList()
            } catch (e: IOException) {
                emit(Resource.Error(message = e.message))
                null
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.message))
                null
            }
            response?.let {
                emit(Resource.Success(data = it.data))
            }
        }
    }

    suspend fun fetchHistoricalData(): Flow<Resource<List<Quote>>> {
        return flow {
            emit(Resource.Loading())
            val response = try {
                api.getHistoricalData(
                    symbol = "OKB",
                    convert = "USD",
                    timeStart = 1672444800, // Start time (Unix timestamp)
                    timeEnd = 1675036800 // End time (Unix timestamp)
                )
            } catch (e: IOException) {
                emit(Resource.Error(message = e.message))
                null
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.message))
                null
            }
            response?.let {
                emit(Resource.Success(data = it.data.values.firstOrNull()?.quotes))
            }
        }
    }
}