package com.beepbeep.designSystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.platform.Font
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource
import org.jetbrains.skiko.loadBytesFromPath

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun fontResources(font: String): Font {
    val scope = rememberCoroutineScope()
    val cache: MutableMap<String, Font> = mutableMapOf()
    return cache.getOrPut(font) {
        var byteArray: ByteArray? = null
        scope.launch {
            byteArray = loadBytesFromPath("font/$font.ttf")
        }
        Font(font, byteArray?: throw Exception("$byteArray"))
    }
}



//@OptIn(ExperimentalResourceApi::class)
//@Composable
//private fun loadPath(font: String): ByteArray {
//
//
//    return byteArray ?: throw Exception("$byteArray")
//}



