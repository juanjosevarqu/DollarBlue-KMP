package com.varqulabs.dollarblue.core.credits.presentation

sealed class CreditsUiEffect {

    data object ShowAd : CreditsUiEffect()

    data class ShowError(
        val message: String,
    ) : CreditsUiEffect()

}