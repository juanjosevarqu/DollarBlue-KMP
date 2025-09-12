package com.varqulabs.dollarblue.core.credits.presentation

interface CreditsEvent {

    data object OnSeeAd: CreditsEvent

    data class SuccessAdWatched(
        val credits: Int,
    ) : CreditsEvent

}