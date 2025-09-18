package com.varqulabs.dollarblue.core.credits.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.varqulabs.dollarblue.core.ads.data.RewardedAdService
import com.varqulabs.dollarblue.core.credits.presentation.CreditsEvent
import com.varqulabs.dollarblue.core.credits.presentation.CreditsScreen
import com.varqulabs.dollarblue.core.credits.presentation.CreditsUiEffect
import com.varqulabs.dollarblue.core.credits.presentation.CreditsViewModel
import com.varqulabs.dollarblue.core.ui.mvi.CollectUiEffect
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import com.varqulabs.dollarblue.core.ui.snackbar.SnackBarController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.compose.koinInject

fun NavGraphBuilder.creditsRoute(
    navController: NavHostController,
    goToSaveConversion: () -> Unit,
) {
    dialog<Routes.Credits> { backStackEntry ->

        val onlyShowInfo = backStackEntry.toRoute<Routes.Credits>().hasEnoughCredits

        val viewModel = koinViewModel<CreditsViewModel>()
        val eventHandler = viewModel::eventHandler
        val uiEffect = viewModel.uiEffect

        val adService = koinInject<RewardedAdService>()

        CreditsScreen(
            hasCredits = onlyShowInfo,
            eventHandler = eventHandler,
        )

        CollectUiEffect(uiEffect) { effect ->
            when(effect) {
                is CreditsUiEffect.ShowAd -> {
                    SnackBarController.showInfo("Cargando anuncio...")
                    adService.showRewardedAd(
                        onReward = {
                            eventHandler(CreditsEvent.SuccessAdWatched(it))
                            SnackBarController.showInfo("Crédito obtenido exitosamente")
                        },
                        onDismissed = {
                            navController.popBackStack()
                            if (onlyShowInfo == false) { goToSaveConversion() }
                        },
                        onError = { SnackBarController.showInfo("Ocurrió un error al cargar el anuncio...") }
                    )
                }
                is CreditsUiEffect.ShowError -> SnackBarController.showInfo(effect.message)
            }
        }
    }
}
