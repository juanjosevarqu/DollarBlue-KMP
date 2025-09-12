package com.varqulabs.dollarblue.core.credits.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.varqulabs.dollarblue.core.credits.presentation.CreditsEvent
import com.varqulabs.dollarblue.core.credits.presentation.CreditsScreen
import com.varqulabs.dollarblue.core.credits.presentation.CreditsUiEffect
import com.varqulabs.dollarblue.core.credits.presentation.CreditsViewModel
import com.varqulabs.dollarblue.core.ui.mvi.CollectUiEffect
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.creditsRoute(
    navController: NavHostController,
    goToSaveConversion: () -> Unit,
) {
    dialog<Routes.Credits> { backStackEntry ->

        val onlyShowInfo = backStackEntry.toRoute<Routes.Credits>().hasEnoughCredits

        val viewModel = koinViewModel<CreditsViewModel>()
        val eventHandler = viewModel::eventHandler
        val uiEffect = viewModel.uiEffect

        //val context = LocalContext.current

        CreditsScreen(
            hasCredits = onlyShowInfo,
            eventHandler = eventHandler,
        )

        CollectUiEffect(uiEffect) { effect ->
            when(effect) {
                is CreditsUiEffect.ShowAd -> {
                    eventHandler(CreditsEvent.SuccessAdWatched(1))
                    navController.popBackStack()
                    if (!onlyShowInfo) { goToSaveConversion() }
                    /*context.showToast(message = "Cargando Anuncio...", duration = LENGTH_LONG)
                    context.showRewardedAd(
                        onReward = {
                            eventHandler(SuccessAdWatched(it))
                            context.showToast(message = "Crédito obtenido exitosamente")
                        },
                        onDismissed = {
                            navController.popBackStack()
                            if (onlyShowInfo == false) { goToSaveConversion() }
                        },
                        onError = { context.showToast(message = "Ocurrió un error al cargar el anuncio...") },
                    )*/
                }
                is CreditsUiEffect.ShowError -> {}//context.showToast(message = effect.message)
            }
        }
    }
}
