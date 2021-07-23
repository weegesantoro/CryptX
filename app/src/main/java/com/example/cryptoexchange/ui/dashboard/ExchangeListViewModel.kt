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

    fun listReset(list: List<Data>) {
        _exchangeList.value = list
    }

    fun getBTCeRates(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withTimeout(30000){
                    val response = BitPayRates.ExchangeListAPI.retrofitService.getBtcResponse()
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


    fun getBCHeRates(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withTimeout(30000){
                    val response = BitPayRates.ExchangeListAPI.retrofitService.getBchResponse()
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



    fun getETHeRates(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withTimeout(30000){
                    val response = BitPayRates.ExchangeListAPI.retrofitService.getEthResponse()
                    withContext(Dispatchers.Main){
                        _exchangeList.value = response.body()?.data
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun getXRPeRates(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withTimeout(30000){
                    val response = BitPayRates.ExchangeListAPI.retrofitService.getXrpResponse()
                    withContext(Dispatchers.Main){
                        _exchangeList.value = response.body()?.data
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


    fun getDOGEeRates(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withTimeout(30000){
                    val response = BitPayRates.ExchangeListAPI.retrofitService.getDogeResponse()
                    withContext(Dispatchers.Main){
                        _exchangeList.value = response.body()?.data
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}