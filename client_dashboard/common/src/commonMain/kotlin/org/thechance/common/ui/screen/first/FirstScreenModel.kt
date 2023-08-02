package org.thechance.common.ui.screen.first

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update
import org.jetbrains.skia.impl.Log


class FirstScreenModel(id: Int) : StateScreenModel<FirstScreenUiState>(FirstScreenUiState()) {

    fun onInputTextChanged(text: String) {
        mutableState.update { it.copy(inputText = text) }
    }

    init {
       println("FirstScreenModel: $id")
    }
}