package dev.pengui.app.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.pengui.app.domain.model.Weather

@Composable
fun WeatherCard(weather: Weather?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Current Weather
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "${weather?.currentTemp ?: "--"}°C",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = weather?.condition ?: "Unknown",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text("High", style = MaterialTheme.typography.labelSmall)
                    Text("${weather?.highTemp ?: "--"}°C", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("Wind", style = MaterialTheme.typography.labelSmall)
                    Text("${weather?.windSpeed ?: "--"} m/s", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Additional Info Row
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherInfoItem("Humidity", "${weather?.humidity ?: "--"}%")
                WeatherInfoItem("Low", "${weather?.lowTemp ?: "--"}°C")
                WeatherInfoItem("Location", weather?.location ?: "Unknown")
            }
        }
    }
}
