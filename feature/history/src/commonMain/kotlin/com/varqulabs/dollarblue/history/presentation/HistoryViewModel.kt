package com.varqulabs.dollarblue.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varqulabs.dollarblue.history.presentation.HistoryEvent.Init
import com.varqulabs.dollarblue.history.presentation.HistoryEvent.OnDeleteConversion
import com.varqulabs.dollarblue.core.credits.domain.usecase.ObserveCredits
import com.varqulabs.dollarblue.core.ui.mvi.MVIContract
import com.varqulabs.dollarblue.core.ui.mvi.MVIDelegate
import com.varqulabs.dollarblue.history.domain.usecase.DeleteCurrencyConversion
import com.varqulabs.dollarblue.history.domain.usecase.GetCurrencyConversionsByMostRecent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HistoryViewModel(
    private val observeCredits: ObserveCredits,
    private val getCurrencyConversionsByMostRecent: GetCurrencyConversionsByMostRecent,
    private val deleteCurrencyConversion: DeleteCurrencyConversion,
    private val dispatcher: CoroutineDispatcher
) : ViewModel(), MVIContract<HistoryState, HistoryEvent, HistoryUiEffect> by MVIDelegate(HistoryState()) {

    override fun eventHandler(event: HistoryEvent) {
        when (event) {
            is Init -> init()
            is OnDeleteConversion -> deleteConversion(event.conversionId)
        }
    }

    private fun init() {
        collectCredits()
        getCurrencyConversions()
    }

    private fun collectCredits() {
        observeCredits()
            .distinctUntilChanged()
            .onEach { updateUi { copy(credits = it) } }
            .launchIn(viewModelScope)
    }

    private fun getCurrencyConversions() {
        viewModelScope.launch(dispatcher) {
            getCurrencyConversionsByMostRecent().collectLatest {
                updateUi { copy(currencyConversions = it) }
            }
        }
    }

    private fun deleteConversion(conversionId: Long) {
        viewModelScope.launch(dispatcher) {
            runCatching { deleteCurrencyConversion(conversionId)  }
                .onFailure { updateUi { copy(isError = true) } }
        }
    }
}