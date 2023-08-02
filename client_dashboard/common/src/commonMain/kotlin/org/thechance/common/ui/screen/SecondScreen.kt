package org.thechance.common.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
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

data class SecondScreen(val name: String) : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Second Screen"
            val icon = rememberVectorPainter(Icons.Default.Favorite)
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon,
                )
            }
        }

    @Composable
    override fun Content() {
        SecondScreenContent()
    }

    @Composable
    private fun SecondScreenContent() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red.copy(0.1f)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello $name!",
                fontSize = 24.sp,
            )
            NextAndPreviousButtons(
                onNextClicked = { navigator.push(ThirdScreen) },
                onPreviousClicked = { navigator.pop() },
                isPreviousButtonEnabled = navigator.canPop,
            )
        }
    }
}