package com.beepbeep.designSystem

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

private val cache: MutableMap<String, Font> = mutableMapOf()

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun fontResources(
    font: String,
    weight: FontWeight,
    style: FontStyle
): Font {
    return cache.getOrPut(font) {
        val byteArray = runBlocking {
            resource("font/$font.ttf").readBytes()
        }
        androidx.compose.ui.text.platform.Font(font, byteArray, weight, style)
    }
}