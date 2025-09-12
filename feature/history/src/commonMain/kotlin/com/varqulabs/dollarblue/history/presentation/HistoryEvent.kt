package com.varqulabs.dollarblue.history.presentation

interface HistoryEvent {

    data object Init : HistoryEvent

    data class OnDeleteConversion(
        val conversionId: Long,
    ) : HistoryEvent

}