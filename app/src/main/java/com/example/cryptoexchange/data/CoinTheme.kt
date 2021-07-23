package com.example.cryptoexchange.data

import com.example.cryptoexchange.R

data class CoinTheme(
    val id: String,
    val displayName: String,
    val color: Int
)
val BtcTheme = CoinTheme("BTC", "Bitcoin (BTC)", R.color.bitcoin_gold)
val BchTheme = CoinTheme("BCH", "Btc Cash (BCH)", R.color.btccash_green)
val EthTheme = CoinTheme("ETH", "Etherium (ETH)", R.color.etherium_blue)
val XrpTheme = CoinTheme("XRP", "Ripple (XRP)", R.color.ripple_black)
val DogeTheme = CoinTheme("DOGE", "Dogecoin (DOGE)", R.color.doge_mustard)