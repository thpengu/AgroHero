package dev.pengui.app.domain.model

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind,
    val name: String
) {
    data class Main(
        val temp: Double,
        val humidity: Int,
        val temp_min: Double,
        val temp_max: Double
    )

    data class Weather(
        val main: String,
        val icon: String
    )

    data class Wind(
        val speed: Double
    )
}