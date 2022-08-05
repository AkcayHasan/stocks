package com.example.stocks.data.repository

import com.example.stocks.data.csv.CSVParser
import com.example.stocks.data.local.StockDatabase
import com.example.stocks.data.mapper.toCompanyListing
import com.example.stocks.data.mapper.toCompanyListingEntity
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
    private val db: StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>
) : StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(localListings.map {
                it.toCompanyListing()
            }))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(isLoading = false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                // for parsing exceptions
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data!"))
                null
            } catch (e: HttpException) {
                // for invalid response
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data!"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListing()
                dao.insertCompanyListing(listings.map {
                    it.toCompanyListingEntity()
                })
                emit(Resource.Success(data = dao.searchCompanyListing("").map {
                    it.toCompanyListing()
                }))
                emit(Resource.Loading(isLoading = false))
            }
        }
    }
}