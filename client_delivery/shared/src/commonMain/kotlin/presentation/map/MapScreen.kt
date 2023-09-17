package presentation.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.beepbeep.designSystem.ui.theme.Theme
import presentation.base.BaseScreen

class MapScreen :
    BaseScreen<MapScreenModel, MapUiState, MapScreenUiEffect, MapInteractionListener>() {
    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    override fun onEffect(effect: MapScreenUiEffect, navigator: Navigator) {
        when (effect) {
            MapScreenUiEffect.Close -> {

            }
        }
    }

    @Composable
    override fun onRender(state: MapUiState, listener: MapInteractionListener) {
        Box(
            modifier = Modifier.fillMaxSize().background(Theme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            BpMapView()
        }
    }
}