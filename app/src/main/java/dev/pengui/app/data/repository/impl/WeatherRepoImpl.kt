package dev.pengui.app.data.repository.impl

import dev.pengui.app.data.repository.WeatherRepository
import dev.pengui.app.domain.model.WeatherData

class WeatherRepoImpl  : WeatherRepository {
    override suspend fun getWeather(): WeatherData {
        // Fetch from weather API
        return WeatherData("24°C", "Sunny", "weather_icon_url")
    }
}