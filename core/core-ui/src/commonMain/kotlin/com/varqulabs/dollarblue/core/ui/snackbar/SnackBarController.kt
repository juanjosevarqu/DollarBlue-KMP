package com.varqulabs.dollarblue.core.ui.snackbar

import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.coroutines.CoroutineContext
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Instant

object SnackBarController {

    private val _events = MutableSharedFlow<SnackEvent>(extraBufferCapacity = 64)
    val events: SharedFlow<SnackEvent> = _events

    private val lastShownAt = mutableMapOf<String, Instant>()

    fun showInfo(
        message: String,
        duration: SnackbarDuration = SnackbarDuration.Short,
        cooldownMs: Long = 600,
        snackbarKey: String = message
    ) {
        if (isDuplicate(snackbarKey, cooldownMs)) return
        _events.tryEmit(SnackEvent.Info(message, duration, snackbarKey))
    }

    fun showAction(
        message: String,
        actionLabel: String,
        originContext: CoroutineContext,
        onAction: suspend () -> Unit,
        duration: SnackbarDuration = SnackbarDuration.Long,
        cooldownMs: Long = 600,
        snackbarKey: String = "$message|$actionLabel"
    ) {
        if (isDuplicate(snackbarKey, cooldownMs)) return
        _events.tryEmit(
            SnackEvent.Action(
                message = message,
                actionLabel = actionLabel,
                originContext = originContext,
                onAction = onAction,
                duration = duration,
                snackbarKey = snackbarKey
            )
        )
    }

    private fun isDuplicate(snackbarKey: String, cooldownMs: Long): Boolean {
        val now: Instant = Clock.System.now()
        val last: Instant? = lastShownAt[snackbarKey]

        return if (last != null && (now - last) < cooldownMs.milliseconds) {
            true
        } else {
            lastShownAt[snackbarKey] = now
            false
        }
    }
}