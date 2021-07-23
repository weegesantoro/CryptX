package com.example.cryptoexchange.network

import com.example.cryptoexchange.data.ExchangeResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

class BitPayRates {

    companion object {

        private const val BASE_URL = "https://bitpay.com/rates/"//example: BTC

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

        @GET("BTC")
        suspend fun getBtcResponse(): Response<ExchangeResponse>

        @GET("BCH")
        suspend fun getBchResponse(): Response<ExchangeResponse>

        @GET("ETH")
        suspend fun getEthResponse(): Response<ExchangeResponse>

        @GET("XRP")
        suspend fun getXrpResponse(): Response<ExchangeResponse>

        @GET("DOGE")
        suspend fun getDogeResponse(): Response<ExchangeResponse>
    }


    object ExchangeListAPI {
        val retrofitService : ExchangeRequest by lazy {
            retrofit.create(ExchangeRequest::class.java) }
    }

}