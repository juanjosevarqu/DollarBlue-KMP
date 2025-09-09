package com.varqulabs.feature.calculator.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope

@Composable
fun LaunchedEffectOnce(
    vararg keys: Any?,
    block: suspend CoroutineScope.() -> Unit,
) {
    var launched by rememberSaveable(*keys) { mutableStateOf(false) }
    val currentBlock by rememberUpdatedState(block)

    if (!launched) {
        LaunchedEffect(Unit) {
            launched = true
            currentBlock()
        }
    }
}
