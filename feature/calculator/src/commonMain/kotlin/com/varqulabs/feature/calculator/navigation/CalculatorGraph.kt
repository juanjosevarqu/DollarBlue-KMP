package com.varqulabs.feature.calculator.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import com.varqulabs.dollarblue.core.ui.navigation.navigateTo
import com.varqulabs.feature.calculator.navigation.routes.calculatorRoute
import com.varqulabs.feature.calculator.navigation.routes.saveConversionRoute

fun NavGraphBuilder.calculatorGraph(
    navController: NavHostController,
    goToHistory: () -> Unit,
    goToWithoutCredits: () -> Unit,
) {
    navigation<Routes.CalculatorGraph>(
        startDestination = Routes.Calculator,
    ) {

        calculatorRoute(
            navController = navController,
            goToHistory = goToHistory,
            onGoToWithoutCredits = goToWithoutCredits,
            onGoToSaveConversion = { navController.navigateTo(Routes.SaveConversion) },
        )

        saveConversionRoute(
            navController = navController,
        )
    }
}