package com.example.cryptoexchange.data


import android.widget.ImageView
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val code: String?,
    val name: String?,
    val rate: Double?,
    @Transient
    var iconId: Int = 0
)