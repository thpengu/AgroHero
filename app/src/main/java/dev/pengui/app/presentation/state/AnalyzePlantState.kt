package dev.pengui.app.presentation.state

sealed class AnalyzePlantState {
    object Idle : AnalyzePlantState()
    object CameraReady : AnalyzePlantState()
    object Analyzing : AnalyzePlantState()
    data class Result(val analysis: dev.pengui.app.domain.usecase.PlantAnalysis) : AnalyzePlantState()
    data class Error(val message: String) : AnalyzePlantState()
}