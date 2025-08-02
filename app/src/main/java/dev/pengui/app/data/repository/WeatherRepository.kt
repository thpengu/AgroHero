package dev.pengui.app.data.repository
import dev.pengui.app.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double):
            Result<Weather>
}