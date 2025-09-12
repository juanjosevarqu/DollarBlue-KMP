package com.varqulabs.dollarblue.welcome.presentation

interface WelcomeEvent {

    data object Init : WelcomeEvent

    data object OnAcceptTerms : WelcomeEvent

}