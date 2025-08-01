package dev.pengui.app.presentation.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pengui.app.data.permission.PermissionManager
import dev.pengui.app.domain.usecase.AnalyzePlantUseCase
import dev.pengui.app.presentation.state.AnalyzePlantState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlantAnalyzerViewModel(
    private val analyzePlant: AnalyzePlantUseCase,
    private val permissionManager: PermissionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnalyzePlantState>(AnalyzePlantState.Idle)
    val uiState: StateFlow<AnalyzePlantState> = _uiState.asStateFlow()

    fun takePhoto(context: Context) {
        viewModelScope.launch {
            permissionManager.requestCameraPermission(
                context = context,
                onGranted = {
                    _uiState.value = AnalyzePlantState.CameraReady
                    // You would typically launch camera intent here
                    // and handle the result in onActivityResult
                },
                onDenied = {
                    _uiState.value = AnalyzePlantState.Error("Camera permission required")
                }
            )
        }
    }

    fun analyzeImage(uri: Uri) {
        viewModelScope.launch {
            _uiState.value = AnalyzePlantState.Analyzing
            try {
                val result = analyzePlant(uri)
                _uiState.value = AnalyzePlantState.Result(result)
            } catch (e: Exception) {
                _uiState.value = AnalyzePlantState.Error(e.message ?: "Analysis failed")
            }
        }
    }
}

