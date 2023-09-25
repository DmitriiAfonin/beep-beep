import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import di.appModule
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.core.context.startKoin
import org.thechance.common.presentation.app.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin { modules(appModule()) }
    onWasmReady {
        CanvasBasedWindow {
            App()
        }
    }
}