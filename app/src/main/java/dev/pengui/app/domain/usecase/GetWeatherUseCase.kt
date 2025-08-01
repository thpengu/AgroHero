package dev.pengui.app.domain.usecase

import dev.pengui.app.data.repository.WeatherRepository
import dev.pengui.app.domain.model.WeatherData

class GetWeatherUseCase (
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(): WeatherData {
        return repository.getWeather()
    }
}