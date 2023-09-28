package org.thechance.common.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import com.beepbeep.designSystem.ui.theme.Theme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.thechance.common.presentation.resources.Resources
import org.thechance.common.presentation.util.kms

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SnackBar(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.colors.surface,
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = backgroundColor)
            .width(512.kms)
            .border(
                width = 1.kms, color = Theme.colors.divider,
                shape = RoundedCornerShape(Theme.radius.medium),
            )
    ) {
        content()
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(Resources.Drawable.close),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Theme.colors.contentPrimary),
            modifier = Modifier.padding(Theme.dimens.space16).clickable(
                onClick = onDismiss
            )
        )
    }
}