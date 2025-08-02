package dev.pengui.app.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.LookaheadScope
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
            Log.d("HomeVM", "Menu items: ${getMenuItems.invoke().size}")
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                Log.d("qalay2", "loadData() called")
                val items = getMenuItems().toList()
                Log.d("qalay2", "Fetched items: ${items.size}")
                val location = locationClient.getCurrentLocation()
                val menuItems = getMenuItems.invoke()
                val weatherData = getWeather.invoke(41.3111, 69.2797) // Example: Tashkent coords

                //val weatherData = getWeather.invoke(location.latitude, location.longitude)
                _uiState.value = _uiState.value.copy(
                    menuItems = menuItems,
                    weatherData = weatherData.getOrNull(),
                    isLoading = false
                )
                Log.d("qalay2", "uiState on vm: ${uiState.value}")
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
                Log.e("qalay2", "Error loading data", e)
            }
        }
    }
}
