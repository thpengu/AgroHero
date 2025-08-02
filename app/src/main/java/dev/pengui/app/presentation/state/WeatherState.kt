package dev.pengui.app.presentation.state

import dev.pengui.app.domain.model.Weather
import dev.pengui.app.domain.model.WeatherResponse

data class WeatherState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)