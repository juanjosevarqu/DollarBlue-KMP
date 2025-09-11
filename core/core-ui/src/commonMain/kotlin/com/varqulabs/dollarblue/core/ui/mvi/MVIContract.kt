package com.varqulabs.dollarblue.core.ui.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MVIContract<State, Event, UiEffect> {

    val uiState: StateFlow<State>
    val uiEffect: SharedFlow<UiEffect>

    val currentUiState: State

    fun eventHandler(event: Event)

    fun updateUi(update: State.() -> State)

    fun CoroutineScope.emitEffect(effect: UiEffect)

}