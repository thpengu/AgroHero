package dev.pengui.app.domain.model

data class PlantDisease(
    val name: String,            // Disease name (e.g., "Powdery Mildew")
    val probability: Float,      // Probability score (0-1)
    val description: String? = null,
    val affectedAreas: List<String> = emptyList() // e.g., ["leaves", "stems"]
)