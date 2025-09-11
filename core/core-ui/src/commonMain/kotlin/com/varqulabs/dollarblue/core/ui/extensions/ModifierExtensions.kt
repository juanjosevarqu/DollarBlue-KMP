package com.varqulabs.dollarblue.core.ui.extensions

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.delay

fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    indication: Indication? = ripple(),
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    var isEnabled by remember { mutableStateOf(enabled) }
    LaunchedEffect(isEnabled) { if (!isEnabled) delay(450L); isEnabled = true }

    this@clickableSingle.clickable(
        enabled = isEnabled,
        onClickLabel = onClickLabel,
        onClick = { onClick(); isEnabled = false },
        role = role,
        indication = indication,
        interactionSource = remember { MutableInteractionSource() }
    )
}

fun Modifier.clickableSingleWithOutRipple(action: () -> Unit): Modifier =
    this@clickableSingleWithOutRipple.clickableSingle(
        indication = null,
        onClick = action,
    )

@Composable
fun Modifier.clickableWithOutRipple(action: () -> Unit): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.clickable(
        interactionSource = interactionSource,
        indication = null
    ) { action() }
}
