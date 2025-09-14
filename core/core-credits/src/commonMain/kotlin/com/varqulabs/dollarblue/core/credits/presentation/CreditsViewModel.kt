package com.varqulabs.dollarblue.core.credits.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dollarblue.core.credits.domain.usecase.AddCredits
import com.varqulabs.dollarblue.core.credits.presentation.CreditsEvent.OnSeeAd
import com.varqulabs.dollarblue.core.credits.presentation.CreditsEvent.SuccessAdWatched
import com.varqulabs.dollarblue.core.credits.presentation.CreditsUiEffect.ShowAd
import com.varqulabs.dollarblue.core.credits.presentation.CreditsUiEffect.ShowError
import com.varqulabs.dollarblue.core.ui.mvi.MVIContract
import com.varqulabs.dollarblue.core.ui.mvi.MVIDelegate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class CreditsViewModel(
    private val addCredits: AddCredits,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel(), MVIContract<CreditsState, CreditsEvent, CreditsUiEffect> by MVIDelegate(CreditsState()) {

    override fun eventHandler(event: CreditsEvent) {
        when (event) {
            is SuccessAdWatched -> onSuccessAdWatched(event.credits)
            is OnSeeAd -> emitAdInvoke()
        }
    }

    private fun emitAdInvoke() = viewModelScope.launch(dispatcher) { emitEffect(ShowAd) }

    private fun onSuccessAdWatched(credits: Int) {
        viewModelScope.launch(dispatcher) {
            runCatching { addCredits(credits) }
                .onSuccess {  }
                .onFailure {
                    updateUi { copy(isError = true) }
                    emitEffect(ShowError(it.message ?: ""))
                }
        }
    }
}