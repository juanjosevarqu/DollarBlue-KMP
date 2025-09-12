package com.varqulabs.dollarblue.core.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Welcome: Routes()


    @Serializable
    data object CalculatorGraph: Routes()

    @Serializable
    data object Calculator: Routes()

    @Serializable
    data object SaveConversion: Routes()


    @Serializable
    data object History: Routes()


    @Serializable
    data class Credits(val hasEnoughCredits: Boolean = true): Routes()

}