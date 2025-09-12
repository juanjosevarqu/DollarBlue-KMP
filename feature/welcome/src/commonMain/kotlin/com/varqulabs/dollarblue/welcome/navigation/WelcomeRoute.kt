package com.varqulabs.dollarblue.welcome.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import com.varqulabs.dollarblue.welcome.presentation.WelcomeTermsDialog

fun NavGraphBuilder.welcomeRoute(
    onAcceptTerms: () -> Unit,
) {
    dialog<Routes.Welcome> {
        WelcomeTermsDialog(
            onAcceptTerms = onAcceptTerms
        )
    }
}