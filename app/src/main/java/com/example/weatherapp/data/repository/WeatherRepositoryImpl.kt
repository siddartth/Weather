package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.domain.data.WeatherValue
import io.reactivex.Single
import javax.inject.Inject


private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

internal class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: Single<WeatherApi>

){
    fun getWeather(
        location: String,
        apiKey: String
    ): WeatherValue? {
        return null
    }
}