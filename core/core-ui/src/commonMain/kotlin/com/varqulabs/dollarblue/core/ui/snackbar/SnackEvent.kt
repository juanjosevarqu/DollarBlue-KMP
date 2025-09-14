package com.varqulabs.dollarblue.core.ui.snackbar

import androidx.compose.material3.SnackbarDuration
import kotlin.coroutines.CoroutineContext

sealed interface SnackEvent {
    val message: String
    val duration: SnackbarDuration

    data class Info(
        override val message: String,
        override val duration: SnackbarDuration = SnackbarDuration.Long,
        val snackbarKey: String = message,
    ) : SnackEvent

    data class Action(
        override val message: String,
        val actionLabel: String,
        val originContext: CoroutineContext,
        val onAction: suspend () -> Unit,
        override val duration: SnackbarDuration = SnackbarDuration.Long,
        val snackbarKey: String = "$message|$actionLabel"
    ) : SnackEvent
}