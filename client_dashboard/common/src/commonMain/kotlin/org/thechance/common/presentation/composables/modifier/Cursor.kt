package org.thechance.common.presentation.composables.modifier

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import org.jetbrains.skiko.Cursor

@Stable
fun Modifier.cursorHoverIconHand() = then(Modifier)
//    then(pointerHoverIcon(
//        androidx.compose.ui.input.pointer.PointerIcon(
//            Cursor.getPredefinedCursor(
//                Cursor.HAND_CURSOR
//            )
//        )
//    ))