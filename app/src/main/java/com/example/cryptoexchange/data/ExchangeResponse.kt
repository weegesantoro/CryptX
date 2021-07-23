package com.example.cryptoexchange.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExchangeResponse(
    val `data`: List<Data>?
)