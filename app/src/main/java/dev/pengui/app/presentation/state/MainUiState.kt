package dev.pengui.app.presentation.state

sealed interface MainUiState {
    object Idle : MainUiState
    object PermissionGranted : MainUiState
    object PermissionDenied : MainUiState
    object ShowRationale : MainUiState
}