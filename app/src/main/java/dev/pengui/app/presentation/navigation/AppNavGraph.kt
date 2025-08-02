package dev.pengui.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.pengui.app.presentation.screen.HomeScreen
import dev.pengui.app.presentation.screen.PlantAnalyzerScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.PlantAnalyzer.route) { PlantAnalyzerScreen(navController) }
        // Add other screens
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object PlantAnalyzer : Screen("analyzer")
}