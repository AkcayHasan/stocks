package com.example.stocks.domain.repository

import com.example.stocks.domain.modal.CompanyListing
import com.example.stocks.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean = true,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}