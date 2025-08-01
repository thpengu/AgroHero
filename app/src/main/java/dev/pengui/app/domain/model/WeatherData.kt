package dev.pengui.app.domain.model


data class WeatherData(
    val currentTemp: Int,
    val highTemp: Int,
    val condition: String,
    val windSpeed: Int,
    val soilTemp: Int,
    val precipitation: Int,
    val humidity: Int,
    val day: String,
    val time: String
)