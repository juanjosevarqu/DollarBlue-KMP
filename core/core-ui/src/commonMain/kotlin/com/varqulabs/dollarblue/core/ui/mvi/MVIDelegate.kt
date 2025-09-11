package com.varqulabs.dollarblue.core.ui.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MVIDelegate<State, Event, UiEffect>(
    initialUiState: State,
) : MVIContract<State, Event, UiEffect> {

    private val _uiState = MutableStateFlow(initialUiState)
    override val uiState: StateFlow<State> = _uiState.asStateFlow()

    override val currentUiState: State get() = _uiState.value

    private val _uiEffect: MutableSharedFlow<UiEffect> by lazy {
        MutableSharedFlow()
    }
    override val uiEffect: SharedFlow<UiEffect> = _uiEffect.asSharedFlow()

    override fun eventHandler(event: Event) {}

    override fun updateUi(update: State.() -> State) {
        _uiState.update { currentState ->
            val newState = currentState.update()
            if (currentState != newState) newState else currentState
        }
    }

    override fun CoroutineScope.emitEffect(effect: UiEffect) {
        this.launch { _uiEffect.emit(effect) }
    }

}