package dev.pengui.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pengui.app.data.repository.LocationClient
import dev.pengui.app.domain.usecase.GetMenuItemUseCase
import dev.pengui.app.domain.usecase.GetWeatherUseCase
import dev.pengui.app.presentation.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMenuItems: GetMenuItemUseCase,
    private val getWeather: GetWeatherUseCase,
    private val locationClient: LocationClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val items = getMenuItems().toList()
                val location = locationClient.getCurrentLocation()
                val menuItems = getMenuItems.invoke()

                /*
                // First check if we have location permission
                val hasLocationPermission = withContext(Dispatchers.IO) {
                    ContextCompat.checkSelfPermission(
                        context(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                }

                val weatherData = if (hasLocationPermission) {
                    try {
                        val location = locationClient.getCurrentLocation()
                        getWeather.invoke(location.latitude, location.longitude)
                    } catch (e: SecurityException) {
                        Log.e("Location", "Permission revoked during operation", e)
                        Result.failure(e)
                    } catch (e: Exception) {
                        Log.e("Location", "Error getting location", e)
                        Result.failure(e)
                    }
                } else {

                    Log.w("Location", "Using default coordinates - no permission")
                    getWeather.invoke(41.3111, 69.2797) // Tashkent coords
                }*/

                val weatherData = getWeather.invoke(location.latitude, location.longitude)
                _uiState.value = _uiState.value.copy(
                    menuItems = menuItems,
                    weatherData = weatherData.getOrNull(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}
