package dev.pengui.app.domain.model

data class PlantAnalysis(
    val isHealthy: Boolean,              // Whether the plant is generally healthy
    val diseases: List<PlantDisease>,    // List of detected diseases
    val confidence: Float,               // Confidence score (0-1)
    val treatmentSuggestions: List<String>, // Recommended treatments
    val scientificName: String? = null,  // Scientific name if identified
    val commonNames: List<String> = emptyList() // Common names
)
