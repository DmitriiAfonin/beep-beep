package org.thechance.common.ui.screen.first


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.thechance.common.ui.composable.NextAndPreviousButtons
import org.thechance.common.ui.screen.SecondScreen


data class FirstScreen(val screenModel: FirstScreenModel) : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "First Screen"
            val icon = rememberVectorPainter(Icons.Default.Home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon,
                )
            }
        }

    @Composable
    override fun Content() {
        val state by screenModel.state.collectAsState()
        FirstScreenContent(
            state,
            screenModel::onInputTextChanged,
        )
    }


    @Composable
    private fun FirstScreenContent(
        state: FirstScreenUiState,
        onInputValueChanged: (String) -> Unit,
    ) {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta.copy(0.1f)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "What's your name?", fontSize = 24.sp)
            TextField(value = state.inputText, onValueChange = onInputValueChanged)
            NextAndPreviousButtons(
                onNextClicked = { navigator.push(SecondScreen(state.inputText)) },
                onPreviousClicked = { navigator.pop() },
                isNextButtonEnabled = state.isInputTextValid,
                isPreviousButtonEnabled = navigator.canPop,
            )
        }
    }
}