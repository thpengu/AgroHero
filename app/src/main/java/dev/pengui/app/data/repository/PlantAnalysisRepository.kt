package dev.pengui.app.data.repository

import android.net.Uri
import dev.pengui.app.domain.usecase.PlantAnalysis

interface PlantAnalysisRepository {
    suspend fun analyzePlant(imageUri: Uri): PlantAnalysis
}

