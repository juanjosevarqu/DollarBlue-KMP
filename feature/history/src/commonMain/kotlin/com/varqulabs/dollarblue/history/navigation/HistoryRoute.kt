package com.varqulabs.dollarblue.history.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dollarblue.history.presentation.HistoryScreen
import com.varqulabs.dollarblue.core.ui.launched_effect.LaunchedEffectOnce
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import com.varqulabs.dollarblue.history.presentation.HistoryEvent.Init
import com.varqulabs.dollarblue.history.presentation.HistoryViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.historyRoute(
    goToCalculator: () -> Unit,
    showCreditsInfo: () -> Unit,
) {
    composable<Routes.History> {

        val viewModel = koinViewModel<HistoryViewModel>()

        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val eventHandler = viewModel::eventHandler

        LaunchedEffectOnce { eventHandler(Init) }

        HistoryScreen(
            state = state,
            eventHandler = eventHandler,
            goToCalculator = goToCalculator,
            showCreditsInfo = showCreditsInfo
        )
    }
}