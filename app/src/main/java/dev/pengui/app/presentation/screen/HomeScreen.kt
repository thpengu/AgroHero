package dev.pengui.app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.pengui.app.presentation.component.AppHeader
import dev.pengui.app.presentation.component.MenuGrid
import dev.pengui.app.presentation.component.WeatherInfo
import dev.pengui.app.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        AppHeader()

        Spacer(modifier = Modifier.height(16.dp))

        MenuGrid(
            items = uiState.menuItems,
            onItemClick = { destination ->
                navController.navigate(destination.route)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeatherInfo(weatherData = uiState.weatherData)
    }
}