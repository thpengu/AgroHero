package dev.pengui.app.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pengui.app.data.repository.LocationClient
import dev.pengui.app.domain.usecase.GetWeatherUseCase
import dev.pengui.app.presentation.state.WeatherState
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeather: GetWeatherUseCase,
    private val locationClient: LocationClient
) : ViewModel() {

    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state

    init {
        getLocationAndWeather()
    }

    private fun getLocationAndWeather() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val location = locationClient.getCurrentLocation()
                val result = getWeather(location.latitude, location.longitude)

                result.onSuccess { weather ->
                    _state.value = _state.value.copy(
                        weather = weather,
                        isLoading = false,
                        error = null
                    )
                }.onFailure { exception ->
                    _state.value = _state.value.copy(
                        error = exception.message,
                        isLoading = false
                    )
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

}