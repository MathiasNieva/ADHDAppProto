package com.example.adhdappprototype.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TagContainer(
    string: String,
    surfaceColor: Color,
    textColor: Color
) {
    Surface(
        border = BorderStroke(1.dp, surfaceColor),
        color = surfaceColor,
        shape = RoundedCornerShape(45),
        elevation =4.dp
    ) {
        Text(
            text = string,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 2.dp),
            color = textColor
        )
    }
}