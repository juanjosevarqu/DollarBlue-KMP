package com.varqulabs.dollarblue

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.varqulabs.dollarblue.core.credits.navigation.creditsRoute
import com.varqulabs.dollarblue.core.ui.launched_effect.LaunchedEffectOnce
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import com.varqulabs.dollarblue.core.ui.navigation.navigateTo
import com.varqulabs.dollarblue.core.ui.snackbar.SnackBarApp
import com.varqulabs.dollarblue.core.ui.snackbar.SnackbarObserver
import com.varqulabs.dollarblue.history.navigation.historyRoute
import com.varqulabs.dollarblue.welcome.navigation.welcomeRoute
import com.varqulabs.dollarblue.welcome.presentation.WelcomeEvent
import com.varqulabs.dollarblue.welcome.presentation.WelcomeViewModel
import com.varqulabs.feature.calculator.navigation.calculatorGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {

    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    val welcomeViewModel = koinViewModel<WelcomeViewModel>()
    val welcomeState by welcomeViewModel.uiState.collectAsStateWithLifecycle()
    val welcomeEventHandler = welcomeViewModel::eventHandler

    LaunchedEffectOnce { welcomeEventHandler(WelcomeEvent.Init) }

    LaunchedEffect(welcomeState.hasAcceptedTerms) {
        if (!welcomeState.hasAcceptedTerms) {
            navController.navigateTo(Routes.Welcome)
        }
    }

    SnackbarObserver(snackBarHostState, navController)

    Scaffold(
        snackbarHost = { SnackBarApp(snackBarHostState) }
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Routes.CalculatorGraph,
        ) {

            welcomeRoute(
                onAcceptTerms = {
                    navController.popBackStack()
                    welcomeEventHandler(WelcomeEvent.OnAcceptTerms)
                }
            )

            calculatorGraph(
                navController = navController,
                goToHistory = { navController.navigateTo(Routes.History) },
                goToWithoutCredits = { navController.navigateTo(Routes.Credits(hasEnoughCredits = false)) },
            )

            historyRoute(
                goToCalculator = { navController.navigateTo(Routes.Calculator) },
                showCreditsInfo = { navController.navigateTo(Routes.Credits()) },
            )

            creditsRoute(
                navController = navController,
                goToSaveConversion = { navController.navigateTo(Routes.SaveConversion) }
            )
        }
    }
}