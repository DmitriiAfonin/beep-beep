package com.beepbeep.designSystem

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
private fun CustomTypography() = Typography(
    defaultFontFamily = FontFamily(
//        fontResources("roboto_regular.ttf", FontWeight.Normal, FontStyle.Normal),
//        fontResources("roboto_medium.ttf", FontWeight.Medium, FontStyle.Normal),
        fontResources("font/borel_regular.ttf", FontWeight.Normal, FontStyle.Normal),
    ),
    h1 = TextStyle(
        fontFamily = FontFamily(fontResources("font/borel_regular.ttf", FontWeight.Normal, FontStyle.Normal))
    )
)

@Composable
fun ApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = CustomTypography(),
        content = content
    )
}