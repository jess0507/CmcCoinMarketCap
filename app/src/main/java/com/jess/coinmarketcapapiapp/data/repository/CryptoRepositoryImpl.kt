package com.jess.coinmarketcapapiapp.data.repository

import com.jess.coinmarketcapapiapp.data.remote.CryptoApi
import com.jess.coinmarketcapapiapp.data.remote.Resource
import com.jess.coinmarketcapapiapp.data.remote.SandboxApi
import com.jess.coinmarketcapapiapp.data.remote.dto.Currency
import com.jess.coinmarketcapapiapp.data.remote.dto.Quote
import com.jess.coinmarketcapapiapp.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val api: CryptoApi,
    private val sendBoxApi: SandboxApi
) : CryptoRepository {

    override suspend fun fetchLatestList(): Flow<Resource<List<Currency>>> {
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

    override suspend fun fetchHistoricalData(symbol: String, convert: String): Flow<Resource<List<Quote>>> {
        return flow {
            emit(Resource.Loading())
            val response = try {
                sendBoxApi.getHistoricalData(
                    symbol = symbol,
                    convert = convert
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