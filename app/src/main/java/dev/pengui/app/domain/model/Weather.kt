package dev.pengui.app.domain.model


data class Weather(
    val location: String,
    val currentTemp: Double,
    val condition: String,
    val iconUrl: String,
    val windSpeed: Double,
    val humidity: Int,
    val highTemp: Double,
    val lowTemp: Double
)