package com.example.stocks.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListing(
        companyListingsEntity: List<CompanyListingsEntity>
    )

    @Query("DELETE FROM companylistingsentity")
    suspend fun clearCompanyListing()

    @Query("SELECT * FROM companylistingsentity WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(:query) == symbol")
    suspend fun searchCompanyListing(query: String): List<CompanyListingsEntity>
}