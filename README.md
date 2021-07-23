# Crypt-X
App showing usage of newest Android Jetpack and Kotlin language features


## Retrofit & Moshi Usage Example

A recycler view was created to show how network requests are made using Retrofit, how the response is handled, and how incoming Json can be parsed with Moshi

<img src="images/retro_moshi_1.png" width="350"/>, <img src="images/retro_moshi_2.png" width="350"/>

*Retrofit just requires a Retrofit.Builder object and an interface to make the request, while Moshi simply needs a model data class.*


### retrofit object

 ```kotlin
private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            //.addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClientBuilder.build())
            .build()
```



### interface

 ```kotlin
interface ExchangeRequest {

        @GET("rates/{currency}")
        suspend fun getBtcResponse(
            @Path("currency") currency: String
        ): Response<ExchangeResponse>
    }
```


### model data class

 ```kotlin
@JsonClass(generateAdapter = true)
data class Data(
    val code: String?,
    val name: String?,
    val rate: Double?,
    @Transient
    var iconId: Int = 0
)
```


## Kotlin Coroutine Example

### request

 ```kotlin
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
```


## LiveData & Observer Example

### data

 ```kotlin
 // set live data with mutable data
private val _exchangeList = MutableLiveData<List<Data>?>()
    val exchangeData: LiveData<List<Data>?> = _exchangeList
    ...
    // set value with response data
    _exchangeList.value = response.body()?.data
```
### observer

 ```kotlin
// Set Adapter Data from live data
        viewModel.exchangeData.observe(viewLifecycleOwner, {
            if(it != null && it.isNotEmpty()){
                currencyAdapter.data = setImageSrc(it)
            }else{
                println("no data to show")
            }
        })
```


## Navigation Graph Usage Example

A navigation graph is a resource file that contains all of your *destinations* and *actions*. The graph represents app's navigation paths. Navigation occurs between *destinations* (fragments etc.). These destinations are connected via *actions*, which can describe animations, transistions, and more.

<img src="images/navigation_graph.png" width="700"/>


