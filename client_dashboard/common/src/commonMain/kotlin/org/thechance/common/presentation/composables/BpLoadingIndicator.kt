package org.thechance.common.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beepbeep.designSystem.ui.theme.Theme
import org.thechance.common.presentation.util.kms

@Composable
fun BpLoadingIndicator(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = isLoading){
        Column (modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            CircularProgressIndicator(
                color = Theme.colors.primary,
                modifier = Modifier
                    .size(48.kms)
                    .fillMaxSize()
            )
        }
    }
}