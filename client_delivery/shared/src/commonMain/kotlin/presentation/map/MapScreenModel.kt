package presentation.map

import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.CoroutineScope
import presentation.base.BaseScreenModel

class MapScreenModel : BaseScreenModel<MapUiState, MapScreenUiEffect>(MapUiState()),
    MapInteractionListener {

    override val viewModelScope: CoroutineScope = coroutineScope

    override fun onClickStart() {
        sendNewEffect(MapScreenUiEffect.Close)
    }


}