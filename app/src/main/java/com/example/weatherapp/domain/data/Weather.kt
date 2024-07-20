package com.example.weatherapp.domain.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherValue(
    val coordinates: Coordinates?,
    @SerializedName("weather")val weather: ArrayList<WeatherInfo>?,
    val base: String?,
    val main: MainInfo?,
    val visibility: Int?,
    val wind: Wind?,
    val clouds: Clouds?,
    val dt: Long?,
    val sys: SysInfo?,
    val id: Int?,
    val name: String?,
    val cod: Int?
)

data class Coordinates(
    @SerializedName("lon" ) var lon : Double? = null,
    @SerializedName("lat" ) var lat : Double? = null
)

@Serializable
data class WeatherInfo(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainInfo(
    @SerializedName("temp"       ) var temp      : Double? = null,
    @SerializedName("feels_like" ) var feelsLike : Double? = null,
    @SerializedName("temp_min"   ) var tempMin   : Double? = null,
    @SerializedName("temp_max"   ) var tempMax   : Double? = null,
    @SerializedName("pressure"   ) var pressure  : Int?    = null,
    @SerializedName("humidity"   ) var humidity  : Int?    = null,
    @SerializedName("sea_level"  ) var seaLevel  : Int?    = null,
    @SerializedName("grnd_level" ) var grndLevel : Int?    = null
)

data class Wind(
    val speed: Float,
    val deg: Int
)

data class Clouds(
    val all: String
)

data class SysInfo(
    val type: Int,
    val id: Int,
    val message: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)