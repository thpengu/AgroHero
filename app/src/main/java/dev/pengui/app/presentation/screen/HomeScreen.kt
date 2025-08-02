package dev.pengui.app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.pengui.app.presentation.component.MenuItemCard
import dev.pengui.app.presentation.component.WeatherCard
import dev.pengui.app.presentation.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "9:41",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Main Title
        Text(
            text = "FARMING",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Menu Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(uiState.menuItems) { item ->
                MenuItemCard(item = item)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Weather Card
        WeatherCard(weather = uiState.weatherData)
    }
}