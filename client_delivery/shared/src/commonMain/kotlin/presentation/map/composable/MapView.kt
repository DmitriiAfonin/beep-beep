package presentation.map.composable


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.entity.Location
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import com.mohamedrejeb.calf.ui.web.WebView
import com.mohamedrejeb.calf.ui.web.rememberWebViewState

@Composable
fun MapView(
    modifier: Modifier,
    currentLocation: Location,
    destination: Location?,
) {
    val state = rememberWebViewState(url = MAP_URL)
    state.settings.javaScriptEnabled = true

    LaunchedEffect(state.isLoading) {
        // Get the current loading state
    }

    AnimatedVisibility(destination == null) {
        LaunchedEffect(true) {
            val jsCode = """
                GetMap();
                clearDirections();
            """.trimIndent()

            state.evaluateJavascript(jsCode, null)
        }
    }

    LaunchedEffect(key1 = currentLocation) {
        destination?.let { location ->
            state.evaluateJavascript("clearDirections()", null)
            state.evaluateJavascript(
                "getDirections(${currentLocation.latitude},${currentLocation.longitude},${location.latitude},${location.longitude})",
                null
            )
        }
    }


    WebView(
        state = state,
        modifier = Modifier.fillMaxSize()
    )
}

private const val MAP_URL = "File:///android_asset/bing_map/map/index.html"
