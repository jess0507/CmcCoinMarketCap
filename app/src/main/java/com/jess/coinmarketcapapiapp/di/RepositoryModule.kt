package com.jess.coinmarketcapapiapp.di

import com.jess.coinmarketcapapiapp.data.repository.CryptoRepositoryImpl
import com.jess.coinmarketcapapiapp.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCryptoRepository(cryptoRepositoryImpl: CryptoRepositoryImpl): CryptoRepository
}