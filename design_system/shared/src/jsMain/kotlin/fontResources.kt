package com.beepbeep.designSystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.platform.Font
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.jetbrains.skiko.loadBytesFromPath

@Composable
actual fun fontResources(font: String): Font = Font(font, loadPath(font))


@Composable
private fun loadPath(font: String): ByteArray {
    val scope = rememberCoroutineScope()
    var test: ByteArray? = null
    scope.launch {
        test = loadBytesFromPath(".../font/$font.ttf")
        joinAll()
    }
    return test ?: ByteArray(1)
}



