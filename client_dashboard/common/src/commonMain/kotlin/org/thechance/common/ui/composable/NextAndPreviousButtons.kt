package org.thechance.common.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NextAndPreviousButtons(
    onNextClicked: () -> Unit,
    onPreviousClicked: () -> Unit,
    isNextButtonEnabled: Boolean = true,
    isPreviousButtonEnabled: Boolean = true,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onPreviousClicked,
            enabled = isPreviousButtonEnabled,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onNextClicked,
            enabled = isNextButtonEnabled,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Next")
        }
    }
}