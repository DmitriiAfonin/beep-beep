package org.thechance.common.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NextAndPreviousButtons(
    onNextClicked: () -> Unit,
    onPreviousClicked: () -> Unit,
    isNextButtonEnabled: Boolean = true,
    isPreviousButtonEnabled: Boolean = true,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onPreviousClicked,
            enabled = isPreviousButtonEnabled
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onNextClicked,
            enabled = isNextButtonEnabled
        ) {
            Text(text = "Next")
        }
    }
}