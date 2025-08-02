package dev.pengui.app.domain.usecase

import dev.pengui.app.data.repository.WeatherRepository
import dev.pengui.app.domain.model.Weather

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Result<Weather> {
        return repository.getWeather(latitude, longitude)
    }
}