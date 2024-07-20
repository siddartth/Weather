package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.data.WeatherValue
import io.reactivex.Single

interface WeatherRepository {
    fun getWeather(
        apiKey: String,
        location: String
    ): Single<WeatherValue>
}