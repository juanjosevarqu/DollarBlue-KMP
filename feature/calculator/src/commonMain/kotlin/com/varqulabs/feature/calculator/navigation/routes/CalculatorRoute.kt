package com.varqulabs.feature.calculator.navigation.routes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.varqulabs.dollarblue.core.ui.launched_effect.LaunchedEffectOnce
import com.varqulabs.dollarblue.core.ui.mvi.CollectUiEffect
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.Init
import com.varqulabs.feature.calculator.presentation.CalculatorScreen
import com.varqulabs.feature.calculator.presentation.CalculatorUIEffect.ConversionSavedSuccessfully
import com.varqulabs.feature.calculator.presentation.CalculatorUIEffect.ShowSaveConversionDialog
import com.varqulabs.feature.calculator.presentation.CalculatorUIEffect.ShowWithoutCreditsDialog
import com.varqulabs.feature.calculator.presentation.CalculatorViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.calculatorRoute(
    navController: NavHostController,
    goToHistory: () -> Unit,
    onGoToSaveConversion: () -> Unit,
    onGoToWithoutCredits: () -> Unit,
) {
    composable<Routes.Calculator> {

        val parentEntry = remember(it) { navController.getBackStackEntry<Routes.CalculatorGraph>() }
        val viewModel = koinViewModel<CalculatorViewModel>(viewModelStoreOwner = parentEntry)

        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val eventHandler = viewModel::eventHandler
        val uiEffect = viewModel.uiEffect

        LaunchedEffectOnce { eventHandler(Init) }

        CalculatorScreen(
            state = state,
            eventHandler = eventHandler,
            goToHistory = goToHistory,
        )

        CollectUiEffect(uiEffect) { effect ->
            when (effect) {
                is ShowSaveConversionDialog -> onGoToSaveConversion()
                is ShowWithoutCreditsDialog -> onGoToWithoutCredits()
                is ConversionSavedSuccessfully -> {} // TODO - Conversion saved successfully feedback
            }
        }
    }
}