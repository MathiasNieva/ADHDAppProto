package com.example.adhdappprototype.data.util

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

enum class Priority (val string: String, val color: Color) {
    LOW("Low", Color.Green),
    MEDIUM("Medium", Color.Yellow),
    HIGH("High", Color.Red)
}