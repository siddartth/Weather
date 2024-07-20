package com.example.weatherapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.domain.data.WeatherValue
import com.example.weatherapp.ui.support.Event
import com.example.weatherapp.ui.support.SearchBarViewState
import com.example.weatherapp.ui.support.UiEvents
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val apiKey: String = "6a38ae68f3f2d3e71a2576d3afe6785e"

class WeatherViewModel @Inject constructor() : BaseViewModel<Event>() {

    private val event: UiEvents<Event> = UiEvents()
    val weather = MutableLiveData<WeatherValue>()
    val isApiError = MutableLiveData<Boolean>()
    val apiErrorMessage = MutableLiveData<String>()

    val searchBarViewState = SearchBarViewState(
        onRootClick = { onSearch() },
        submitQuery = { onSearch() },
        hint = MutableLiveData(
            "type a city name"
        )
    )

    init {
        apiErrorMessage.postValue("")
        isApiError.postValue(false)
        getData( "san fransisco")
    }

    private fun onSearch() {
        event.post(Event.OnKeyboardClose)
        searchBarViewState.query?.value.takeUnless { it.isNullOrBlank() }?.also {
            state.value = State.LOADING
            getData(it.ifEmpty { "san fransisco"})
        }
    }



    private fun getData(location: String) {
        val apiBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
        apiBuilder.getWeather(
            location = location,
            apiKey = apiKey
        ).enqueue(object : Callback<WeatherValue> {
            override fun onResponse(call: Call<WeatherValue>, response: Response<WeatherValue>) {
                if (response.message() == "Not Found")
                {
                    isApiError.postValue(true)
                    apiErrorMessage.postValue(response.message())
                    state.value = State.ERROR
                }
                else {
                    handleSuccess(response)
                    Log.i("WEATHER_LOG", "Success: ${response.body()} $call ${response.message()}")
                }
            }

            override fun onFailure(call: Call<WeatherValue>, t: Throwable) {
                state.value = State.ERROR
                Log.i("WEATHER_LOG", t.message.toString())
                isApiError.postValue(true)
            }

        })
    }

    private fun handleSuccess(response: Response<WeatherValue>): WeatherValue? {
        weather.postValue(response.body())
        state.value = State.SUCCESS
        return response.body()
    }
}