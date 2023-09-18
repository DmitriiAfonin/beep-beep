package presentation.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.beepbeep.designSystem.ui.composable.BpAppBar
import com.beepbeep.designSystem.ui.theme.Theme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.base.BaseScreen
import presentation.map.composable.NewOrderRequest
import resources.Resources


class MapScreen:BaseScreen<MapScreenModel,MapScreenUiState,MapScreenUiEffect,MapScreenInteractionsListener>() {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun onRender(state: MapScreenUiState, listener: MapScreenInteractionsListener) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            BpAppBar(
                isBackIconVisible = false,
                title = "${Resources.strings.welcome}${state.username}"
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .width(40.dp)
                        .height(40.dp)
                        .background(
                            color = Theme.colors.hover,
                            shape = RoundedCornerShape(size = 8.dp)
                        ).clickable { listener.onCloseClicked() }
                        .align(Alignment.Center)
                        .padding(12.dp),
                    painter = painterResource(Resources.images.close),
                    contentDescription = Resources.strings.close,
                    tint = Theme.colors.contentPrimary
                )
            }

            AnimatedVisibility(modifier = Modifier.align(Alignment.BottomCenter),
                visible = true,
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                NewOrderRequest(state, listener)
            }
        }
    }

    override fun onEffect(effect: MapScreenUiEffect, navigator: Navigator) {
        when(effect) {
            MapScreenUiEffect.CloseMap -> navigator.pop()
        }
    }

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }
}
