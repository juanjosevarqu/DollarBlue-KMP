package com.varqulabs.dollarblue.welcome.presentation

import androidx.compose.runtime.Stable

@Stable
data class WelcomeState(
    val hasAcceptedTerms: Boolean = true,
)