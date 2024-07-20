package com.example.weatherapp.data.api

import com.example.weatherapp.domain.data.WeatherValue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): Call<WeatherValue>

}