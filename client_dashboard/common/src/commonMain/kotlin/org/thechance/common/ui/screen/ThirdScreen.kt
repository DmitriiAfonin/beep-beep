package org.thechance.common.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import kotlinx.coroutines.launch
import org.thechance.common.ui.composable.NextAndPreviousButtons

object ThirdScreen : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Third Screen"
            val icon = rememberVectorPainter(Icons.Default.Face)
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon,
                )
            }
        }

    @Composable
    override fun Content() {
        ThirdScreenContent()
    }

    @Composable
    private fun ThirdScreenContent() {
        val coroutineScope = rememberCoroutineScope()
        val navigator = LocalNavigator.currentOrThrow
        val scale = remember { Animatable(0f) }

        LifecycleEffect(
            onStarted = {
                coroutineScope.launch {
                    scale.animateTo(
                        1f,
                        animationSpec = TweenSpec(durationMillis = 2000),
                    )
                }
            },
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue.copy(0.1f)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "It's was a pleasure to meet you!",
                fontSize = 24.sp,
                modifier = Modifier.scale(scale.value)
            )
            NextAndPreviousButtons(
                onNextClicked = { navigator.popUntilRoot() },
                onPreviousClicked = { navigator.pop() },
                isPreviousButtonEnabled = navigator.canPop,
            )
        }
    }
}