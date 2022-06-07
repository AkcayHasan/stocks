package com.example.stocks.data.repository

import com.example.stocks.data.local.StockDatabase
import com.example.stocks.data.mapper.toCompanyListing
import com.example.stocks.data.remote.StockService
import com.example.stocks.domain.modal.CompanyListing
import com.example.stocks.domain.repository.StockRepository
import com.example.stocks.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockService,
    private val db: StockDatabase
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading())
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(localListings.map {
                it.toCompanyListing()
            }))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                
            } catch (e: IOException) {
                // for parsing exceptions
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data!"))
            } catch (e: HttpException) {
                // for invalid response
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data!"))
            }
        }
    }
}