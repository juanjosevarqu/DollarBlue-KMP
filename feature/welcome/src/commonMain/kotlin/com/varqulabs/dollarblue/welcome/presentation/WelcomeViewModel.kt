package com.varqulabs.dollarblue.welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dollarblue.core.ui.mvi.MVIContract
import com.varqulabs.dollarblue.core.ui.mvi.MVIDelegate
import com.varqulabs.dollarblue.welcome.domain.usecase.AcceptTerms
import com.varqulabs.dollarblue.welcome.domain.usecase.ObserveTermsAccepted
import com.varqulabs.dollarblue.welcome.presentation.WelcomeEvent.Init
import com.varqulabs.dollarblue.welcome.presentation.WelcomeEvent.OnAcceptTerms
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val observeTermsAccepted: ObserveTermsAccepted,
    private val acceptTerms: AcceptTerms,
    private val dispatcher: CoroutineDispatcher
) : ViewModel(), MVIContract<WelcomeState, WelcomeEvent, WelcomeUiEffect> by MVIDelegate(WelcomeState()) {

    override fun eventHandler(event: WelcomeEvent) {
        when (event) {
            is Init -> checkIfTermsAccepted()
            is OnAcceptTerms -> onAcceptTerms()
        }
    }

    private fun checkIfTermsAccepted() {
        viewModelScope.launch(dispatcher) {
            observeTermsAccepted().collectLatest { hasAcceptedTerms ->
                updateUi { copy(hasAcceptedTerms = hasAcceptedTerms) }
            }
        }
    }

    private fun onAcceptTerms() { viewModelScope.launch(dispatcher) { acceptTerms() } }
}