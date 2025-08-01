package dev.pengui.app.data.repository

import dev.pengui.app.domain.model.WeatherData

interface WeatherRepository {
    suspend fun getWeather(): WeatherData
}