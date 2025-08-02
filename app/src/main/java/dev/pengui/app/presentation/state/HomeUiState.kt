package dev.pengui.app.presentation.state

import androidx.compose.runtime.Stable
import dev.pengui.app.domain.model.MenuItem
import dev.pengui.app.domain.model.Weather

data class HomeUiState(
    val menuItems: List<MenuItem> = emptyList(),
    val weatherData: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val stableMenuItems: List<MenuItem> @Stable get() = menuItems
}

