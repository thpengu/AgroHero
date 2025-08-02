package dev.pengui.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import dev.pengui.app.presentation.state.MainUiState
class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun onPermissionGranted() {
        _uiState.value = MainUiState.PermissionGranted
    }

    fun onPermissionDenied() {
        _uiState.value = MainUiState.PermissionDenied
    }

    fun showRationale() {
        _uiState.value = MainUiState.ShowRationale
    }
}