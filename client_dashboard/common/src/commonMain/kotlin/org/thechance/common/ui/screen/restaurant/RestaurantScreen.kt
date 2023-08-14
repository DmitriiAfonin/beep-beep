package org.thechance.common.ui.screen.restaurant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.beepbeep.designSystem.ui.composable.BpButton

object RestaurantScreen: Screen {

    @Composable
    override fun Content() {
        RestaurantContent(
            onClickBack = {

            }
        )
    }

  @Composable
  private  fun RestaurantContent(
        onClickBack: () -> Unit,
    ) {

        Column(
            Modifier.fillMaxSize().background(color = Color.Cyan),
        ) {
            Box(Modifier.weight(1f)) {
                Text(text = "Restaurant Screen")
            }
        }
    }
}