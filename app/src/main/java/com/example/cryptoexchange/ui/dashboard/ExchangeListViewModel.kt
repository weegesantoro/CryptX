package com.example.cryptoexchange.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoexchange.data.Data
import com.example.cryptoexchange.network.BitPayRates
import kotlinx.coroutines.*

class ExchangeListViewModel : ViewModel() {


    private val _exchangeList = MutableLiveData<List<Data>?>()
    val exchangeData: LiveData<List<Data>?> = _exchangeList


    fun getRates(code: String){
        sendRequest(code)
    }

    private fun sendRequest(currency: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                withTimeout(30000){
                    val response = BitPayRates.ExchangeListAPI.retrofitService.getRateResponse(currency)
                    withContext(Dispatchers.Main){
                        _exchangeList.value = response.body()?.data
                        println("response.body()?.data = ${response.body()}")
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}