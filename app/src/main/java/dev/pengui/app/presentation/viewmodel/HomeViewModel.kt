package dev.pengui.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pengui.app.domain.usecase.GetMenuItemUseCase
import dev.pengui.app.domain.usecase.GetWeatherUseCase
import dev.pengui.app.presentation.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMenuItems: GetMenuItemUseCase,
    private val getWeather: GetWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val menuItems = getMenuItems.invoke()
                val weatherData = getWeather.invoke()
                _uiState.value = _uiState.value.copy(
                    menuItems = menuItems,
                    weatherData = weatherData,
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
