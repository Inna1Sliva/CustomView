package com.example.chatapp_2_0.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun AudioLevel() {
    var audioLevels by remember { mutableStateOf(List(20) { 0f }) }
    LaunchedEffect(Unit) {
        while (true) {
            audioLevels = generateRandomLevels()
            delay(100)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            val width = size.width
            val height = size.height

            val barCount = audioLevels.size
            val barWidth = width / barCount


            for (i in audioLevels.indices) {

                // Высота вертикальной линии
                val barHeight = height * audioLevels[i]

                val offsetX = i * barWidth + barWidth / 2

                // Рисуем горизонтальный индикатор прогресс-бара
                drawRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Yellow,
                            Color.Red,
                            Color.Magenta
                        )
                    ),

                    topLeft = Offset(
                        offsetX,
                        height - barHeight
                    ), // Начальная точка от нижней части
                    size = androidx.compose.ui.geometry.Size(
                        barWidth - 12f,
                        barHeight
                    ) // Заполненная ширина
                )

            }

        }


    }


}
// Функция для генерации случайных уровней звука от 0 до 20
private fun generateRandomLevels(): List<Float> {
    return List(20) { Random.nextFloat() }// Генерируем 20 случайных уровней
}