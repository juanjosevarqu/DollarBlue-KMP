package com.varqulabs.feature.calculator.navigation.routes

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import com.varqulabs.dollarblue.core.ui.navigation.navigateBackWithUp
import com.varqulabs.feature.calculator.presentation.CalculatorEvent
import com.varqulabs.feature.calculator.presentation.CalculatorViewModel
import com.varqulabs.feature.calculator.presentation.components.SaveConversionScreen
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.saveConversionRoute(
    navController: NavHostController,
) {
    dialog<Routes.SaveConversion> {

        val parentEntry = remember(it) { navController.getBackStackEntry<Routes.CalculatorGraph>() }
        val viewModel = koinViewModel<CalculatorViewModel>(viewModelStoreOwner = parentEntry)

        SaveConversionScreen(
            onSaveConversion = { name ->
                viewModel.eventHandler(CalculatorEvent.OnSaveConversion(name))
                navController.navigateBackWithUp()
            }
        )
    }
}