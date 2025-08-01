package dev.pengui.app.data.repository.impl

import dev.pengui.app.data.repository.WeatherRepository
import dev.pengui.app.domain.model.WeatherData

class WeatherRepoImpl  : WeatherRepository {
    override suspend fun getWeather(): WeatherData {
        // Fetch from weather API
        return WeatherData(24, 12, "23", 12, 12, 12, 12, "12", "34" )
    }
}