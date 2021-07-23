package com.example.cryptoexchange.network

import com.example.cryptoexchange.data.ExchangeResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

class BitPayRates {

    companion object {

        private const val BASE_URL = "https://bitpay.com/"//example: BTC

        private val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)


        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClientBuilder.build())
            .build()

    }


    interface ExchangeRequest {

        @GET("rates/{currency}")
        suspend fun getBtcResponse(
            @Path("currency") currency: String
        ): Response<ExchangeResponse>
    }


    object ExchangeListAPI {
        val retrofitService : ExchangeRequest by lazy {
            retrofit.create(ExchangeRequest::class.java) }
    }

}