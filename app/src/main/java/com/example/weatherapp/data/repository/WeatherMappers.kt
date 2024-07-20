package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.data.WeatherValue

internal fun WeatherValue.toDomain(): WeatherValue {
    return WeatherValue(
        coordinates = coordinates,
        weather = weather,
        base = base,
        main = main,
        visibility = visibility,
        wind = wind,
        clouds = clouds,
        dt = dt,
        sys = sys,
        id = id,
        name = name,
        cod = cod
    )
}