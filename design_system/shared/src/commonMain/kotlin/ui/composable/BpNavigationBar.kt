package com.beepbeep.designSystem.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.beepbeep.designSystem.ui.theme.Theme

@Composable
fun BpSideBar(
    painter: Painter,
    sizeWidth: Dp = 100.dp,
    modifier: Modifier = Modifier,
    elevation: Dp = 8.dp,
    backgroundColor: Color = Theme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    horizontalArrangement: Alignment.Horizontal = Alignment.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceBetween,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        shadowElevation = elevation,
        modifier = modifier
    ) {
        var sidebarExpanded by remember { mutableStateOf(false) }

        Column(
            modifier = modifier
                .width(if (sidebarExpanded) 250.dp else sizeWidth)
                .horizontalScroll(rememberScrollState()),
            horizontalAlignment = horizontalArrangement,
            verticalArrangement = verticalArrangement,
        ) {
            Column(
                Modifier.selectableGroup().padding(vertical = 40.dp),
                horizontalAlignment = horizontalArrangement,
                verticalArrangement = verticalArrangement,
                content = {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 40.dp)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    content()
                }
            )
            BpToggleButton(
                isDark = false,
                onToggle = { /*TODO*/ },
                modifier = Modifier.padding(start = 40.dp, bottom = 40.dp)
            )
        }
    }
}

@Composable
fun ColumnScope.BpNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable (tint: Color) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable ((style: TextStyle) -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val styledIcon = @Composable {
        val iconColor by animateColorAsState(
            if (selected) Theme.colors.primary else Theme.colors.contentSecondary,
            tween(500)
        )
        icon(tint = iconColor)
    }
    val styledLabel = @Composable {
        val textColor by animateColorAsState(
            if (selected) Theme.colors.primary else Theme.colors.contentSecondary,
            tween(500)
        )
        val style =
            Theme.typography.caption.copy(color = textColor)
        label?.let {
            it(style)
        }
    }

    Row(
        modifier.selectable(
            indication = null,
            interactionSource = interactionSource,
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            role = Role.Tab,
        ).selectableGroup().height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        AnimatedVisibility(selected) {
            Indicator(4.dp, height = 40.dp)
        }

        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            styledIcon()
//            AnimatedVisibility(alwaysShowLabel || (selected && label != null)) {
//                Spacer(modifier = Modifier.width(16.dp))
//                styledLabel()
//            }
        }
    }
}

@Composable
private fun Indicator(
    width: Dp,
    height: Dp = 2.dp,
    cornerRadius: Float = 4f,
    color: Color = Theme.colors.primary,
) {
    Canvas(modifier = Modifier.size(width, height)) {
        drawRoundRect(
            color = color,
            cornerRadius = CornerRadius(cornerRadius)
        )
    }
}