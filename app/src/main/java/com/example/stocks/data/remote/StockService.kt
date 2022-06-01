package com.example.stocks.data.remote

import com.example.stocks.util.Constants
import okhttp3.ResponseBody
import retrofit2.http.GET

interface StockService {

    @GET(Constants.UrlConstants.listings)
    suspend fun getListings(): ResponseBody
}