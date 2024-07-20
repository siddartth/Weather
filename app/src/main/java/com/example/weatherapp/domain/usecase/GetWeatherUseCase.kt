package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.data.WeatherValue
import io.reactivex.Single

/**
 * Get current weather for specified location in us
 *
 * Errors: [UnknownError]
 *
 * @see
 */

interface GetWeatherUseCase {
    fun execute(location: String): Single<WeatherValue>

    suspend fun coExecute(location: String): WeatherValue
}