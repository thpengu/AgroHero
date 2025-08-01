package dev.pengui.app.domain.usecase

import android.net.Uri
import dev.pengui.app.data.repository.PlantAnalysisRepository
import dev.pengui.app.domain.model.PlantDisease

class AnalyzePlantUseCase(
    private val repository: PlantAnalysisRepository
) {
    suspend operator fun invoke(imageUri: Uri): PlantAnalysis {
        return repository.analyzePlant(imageUri)
    }
}

// domain/model/PlantAnalysis.kt
data class PlantAnalysis(
    val isHealthy: Boolean,
    val diseases: List<PlantDisease>,
    val treatmentSuggestions: List<String>,
    val confidence: Float
)
