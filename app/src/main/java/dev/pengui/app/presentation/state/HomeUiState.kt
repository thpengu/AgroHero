package dev.pengui.app.presentation.state

import dev.pengui.app.domain.model.MenuItem
import dev.pengui.app.domain.model.Weather

data class HomeUiState(
    val menuItems: List<MenuItem> = emptyList(),
    val weatherData: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)