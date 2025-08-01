package dev.pengui.app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import dev.pengui.app.presentation.state.AnalyzePlantState
import dev.pengui.app.presentation.viewmodel.PlantAnalyzerViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.collections.isNotEmpty

@Composable
fun PlantAnalyzerScreen(viewModel: PlantAnalyzerViewModel = koinViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    when (val currentState = state) {
        is AnalyzePlantState.Idle -> Button(
            onClick = { viewModel.takePhoto(context) },
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Analyze Plant")
        }
        is AnalyzePlantState.Analyzing -> CircularProgressIndicator()
        is AnalyzePlantState.Result -> AnalysisResult(currentState.analysis)
        is AnalyzePlantState.Error -> Text(currentState.message, color = Color.Red)

        AnalyzePlantState.CameraReady -> {
            TODO()
        }
    }
}

@Composable
fun AnalysisResult(analysis: dev.pengui.app.domain.usecase.PlantAnalysis) {
    Column {
        Text("Analysis Results", style = MaterialTheme.typography.headlineMedium)
        Text("Status: ${if (analysis.isHealthy) "Healthy" else "Unhealthy"}")

        if (analysis.diseases.isNotEmpty()) {
            Text("Detected Issues:")
            analysis.diseases.forEach { disease ->
                Text("- ${disease.name} (${(disease.probability * 100).toInt()}%)")
            }
        }

        if (analysis.treatmentSuggestions.isNotEmpty()) {
            Text("Recommended Treatments:")
            analysis.treatmentSuggestions.forEach { suggestion ->
                Text("- $suggestion")
            }
        }
    }
}