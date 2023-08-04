package ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator

object SheetContent : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalBottomSheetNavigator.current
        LazyColumn {

            item {
                IconButton(onClick = {
                    navigator.hide()
                }) {
                    Icon(Icons.Default.Close, contentDescription = "")
                }
            }

            items(10) { data ->
                Text("count $data")
            }

        }
    }

}