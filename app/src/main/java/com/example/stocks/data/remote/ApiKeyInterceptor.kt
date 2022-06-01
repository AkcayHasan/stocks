package com.example.stocks.data.remote

import com.example.stocks.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = Constants.ApiConstants.API_KEY

        val url = original.url.newBuilder().addQueryParameter("apikey", token).build()
        original = original.newBuilder().url(url).build()

        return chain.proceed(original)
    }
}