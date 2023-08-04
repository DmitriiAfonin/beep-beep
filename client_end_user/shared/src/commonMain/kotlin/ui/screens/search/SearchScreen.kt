package ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import di.getScreenModel
import ui.screens.details.DetailsScreen
import ui.uistate.SearchUiState


object SearchScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<SearchViewModel>()
        val state by screenModel.state.collectAsState()
        SearchContent(
            state = state,
            onInputValueChanged = screenModel::onInputValueChanged
        )
    }

    @Composable
    private fun SearchContent(
        state: SearchUiState,
        onInputValueChanged: (String) -> Unit,
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            TextField(value = state.text, onValueChange = onInputValueChanged)
            Button(onClick = {
                navigator.push(DetailsScreen(state.text))
                println("clicked:$navigator")
            }) {
                Text("Search")
            }
        }
    }
}



