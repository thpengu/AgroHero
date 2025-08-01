package dev.pengui.app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.pengui.app.domain.model.WeatherData


@Composable
fun WeatherCard(weatherData: WeatherData?) {
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
                        text = "${weatherData?.currentTemp}°C",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = weatherData?.condition ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                // Weather details
                Column(horizontalAlignment = Alignment.End) {
                    Text("High", style = MaterialTheme.typography.labelSmall)
                    Text("${weatherData?.highTemp}°C", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("Wind", style = MaterialTheme.typography.labelSmall)
                    Text("${weatherData?.windSpeed}m/s", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Additional weather info
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherInfoItem("Soil Temp", "${weatherData?.soilTemp}°C")
                WeatherInfoItem("Precipitation", "${weatherData?.precipitation}mm")
                WeatherInfoItem("Humidity", "${weatherData?.humidity}%")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Day and time
            Text(
                text = "${weatherData?.day} ${weatherData?.time}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}