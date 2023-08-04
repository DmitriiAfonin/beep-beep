package ui.screens.search

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.uistate.SearchUiState


class SearchViewModel : StateScreenModel<SearchUiState>(SearchUiState()) {

    fun onInputValueChanged(text: String) {
        mutableState.update { it.copy(text = text) }
    }

    fun getResults() {
        coroutineScope.launch {
            //request to server
        }
    }
}