package dev.pengui.app.data.datasource.mapper

import dev.pengui.app.domain.model.Weather
import dev.pengui.app.domain.model.WeatherResponse

internal fun WeatherResponse.toDomainModel(): Weather {
    return Weather(
        location = this.name,
        currentTemp = this.main.temp,
        condition = this.weather.firstOrNull()?.main ?: "Unknown",
        iconUrl = this.weather.firstOrNull()?.icon ?: "",
        windSpeed = this.wind.speed,
        humidity = this.main.humidity,
        highTemp = this.main.temp_max,
        lowTemp = this.main.temp_min
    )
}