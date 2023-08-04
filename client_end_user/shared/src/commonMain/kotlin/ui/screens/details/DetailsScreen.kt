package ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import di.getScreenModel
import org.koin.core.parameter.parametersOf
import ui.uistate.DetailsUiState

data class DetailsScreen(val text: String) : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<DetailsViewModel> { parametersOf(text) }
        val state by screenModel.state.collectAsState()

        DetailsScreenContent(
            state = state,
        )
    }

    @Composable
    private fun DetailsScreenContent(
        state: DetailsUiState,
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "details screen", modifier = Modifier, fontSize = 65.sp)
            Button(onClick = {
                navigator.pop()
                println("clicked:$navigator")
            }) {
                Text("Back")
            }
            Button(onClick = {
                navigator.push(SecondScreen())
                println("clicked:$navigator")
            }) {
                Text("go to second screen")
            }
            Text(text = state.text, modifier = Modifier.fillMaxWidth(), fontSize = 65.sp)

        }

    }
}
