package com.varqulabs.feature.calculator.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.varqulabs.feature.calculator.core.LaunchedEffectOnce
import com.varqulabs.feature.calculator.core.mvi.CollectUiEffect
import com.varqulabs.feature.calculator.presentation.CalculatorEvent.Init
import com.varqulabs.feature.calculator.presentation.CalculatorScreen
import com.varqulabs.feature.calculator.presentation.CalculatorUIEffect.ConversionSavedSuccessfully
import com.varqulabs.feature.calculator.presentation.CalculatorUIEffect.ShowSaveConversionDialog
import com.varqulabs.feature.calculator.presentation.CalculatorUIEffect.ShowWithoutCreditsDialog
import com.varqulabs.feature.calculator.presentation.CalculatorViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
sealed class Routes {

    @Serializable
    data object Calculator : Routes()
}

fun NavGraphBuilder.calculatorRoute(
    navController: NavHostController,
    goToHistory: () -> Unit,
    onGoToSaveConversion: () -> Unit,
    onGoToWithoutCredits: () -> Unit,
) {
    composable<Routes.Calculator> {

        val viewModel = koinViewModel<CalculatorViewModel>()

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