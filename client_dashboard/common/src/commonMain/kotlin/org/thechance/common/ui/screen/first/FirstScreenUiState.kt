package org.thechance.common.ui.screen.first

data class FirstScreenUiState(
    val inputText: String = "",
) {
    val isInputTextValid: Boolean = inputText.isNotBlank()
}
