package com.example.stocks.di

import com.example.stocks.data.csv.CSVParser
import com.example.stocks.data.csv.CompanyListingsParser
import com.example.stocks.data.repository.StockRepositoryImpl
import com.example.stocks.domain.modal.CompanyListing
import com.example.stocks.domain.repository.StockRepository
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
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ) : CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}