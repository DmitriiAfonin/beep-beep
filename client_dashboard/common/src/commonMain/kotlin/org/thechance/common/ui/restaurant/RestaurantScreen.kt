package org.thechance.common.ui.restaurant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.beepbeep.designSystem.ui.theme.Theme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.thechance.common.ui.main.MainScreenModel

object RestaurantScreen : Screen , KoinComponent {

    private val screenModel: RestaurantScreenModel by inject()
    @Composable
    override fun Content() {
        RestaurantContent()
    }

    @Composable
    private fun RestaurantContent() {

        Column(
            Modifier.background(Theme.colors.surface).fillMaxSize()
        ) {
            Box(Modifier.weight(1f)) {
                Text(text = "Restaurant Screen")
            }
        }
    }
}