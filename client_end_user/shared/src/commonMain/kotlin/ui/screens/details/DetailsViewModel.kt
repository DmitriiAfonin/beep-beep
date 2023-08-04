package ui.screens.details

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.uistate.DetailsUiState


class DetailsViewModel(
    val text: String,
) : StateScreenModel<DetailsUiState>(DetailsUiState()) {

    init {
        mutableState.update { it.copy(text = text) }
        println("iniiiiiiiiiit:$text")
    }

    fun getResults() {
        coroutineScope.launch {
            mutableState.update { it.copy(text = text) }
            //request to server
        }
    }
}