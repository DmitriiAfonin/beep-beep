package org.thechance.common.ui.screen.first

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update


class FirstScreenModel : StateScreenModel<FirstScreenUiState>(FirstScreenUiState()) {

    fun onInputTextChanged(text: String) {
        mutableState.update { it.copy(inputText = text) }
    }
}