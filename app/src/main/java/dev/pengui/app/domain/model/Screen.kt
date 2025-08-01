package dev.pengui.app.domain.model

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Shop : Screen("shop")
}