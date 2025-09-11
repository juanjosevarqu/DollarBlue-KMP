package com.varqulabs.dollarblue.core.ui.launched_effect

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun LaunchPulseEffect(
    key: Any,
    durationMillis: Int = 400,
    pulseScale: Float = 1.15f,
    content: @Composable (Modifier) -> Unit
) {

    val scale = remember { Animatable(1f) }

    LaunchedEffect(key) {
        scale.animateTo(pulseScale, animationSpec = tween(durationMillis))
        scale.animateTo(1f, animationSpec = tween(durationMillis))
    }

    content(
        Modifier.graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
            transformOrigin = TransformOrigin.Center
        }
    )
}