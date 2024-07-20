package com.example.weatherapp.data.usecase

import com.example.weatherapp.domain.data.WeatherValue
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import io.reactivex.Single
import javax.inject.Inject


private val apiKey: String = ""

class GetWeatherUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
): GetWeatherUseCase {

    override fun execute(location: String): Single<WeatherValue> {
        return weatherRepository.getWeather(
            location = location,
            apiKey = apiKey
        )
    }

    override suspend fun coExecute(location: String): WeatherValue {
        return execute("").blockingGet()
    }

}