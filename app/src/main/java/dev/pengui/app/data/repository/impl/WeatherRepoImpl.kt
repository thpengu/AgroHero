package dev.pengui.app.data.repository.impl

import dev.pengui.app.data.datasource.remote.WeatherApi
import dev.pengui.app.data.datasource.mapper.toDomainModel
import dev.pengui.app.data.repository.WeatherRepository
import dev.pengui.app.domain.model.Weather

class WeatherRepoImpl(
    private val weatherApi: WeatherApi,
    private val apiKey: String
) : WeatherRepository {

    override suspend fun getWeather(
        lat: Double,
        lon: Double
    ): Result<Weather> {
        return try {
            val response = weatherApi.getWeather(
                lat = lat,
                lon = lon,
                units = "metric",
                apiKey = apiKey
            )

            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    Result.success(apiResponse.toDomainModel())
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("API error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

