package com.varqulabs.dollarblue.core.ui.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController

fun NavHostController.goToHomeCalculator() {
    if (currentBackStackEntry?.destination?.hasRoute<Routes.Calculator>() == true) return

    val restoreHome = popBackStack<Routes.Calculator>(inclusive = false)
    if (restoreHome) return

    navigate(Routes.Calculator) {
        popUpTo<Routes.CalculatorGraph> { saveState = true }
        restoreState = true
        launchSingleTop = true
    }
}

inline fun <reified T : Routes> NavHostController.navigateTo(route: T) {
    if (route is Routes.Calculator || route is Routes.CalculatorGraph) { goToHomeCalculator(); return }

    val canReuseInstance = popBackStack<T>(inclusive = false)
    if (canReuseInstance) return

    navigate(route) {
        launchSingleTop = true
    }
}

private val NavHostController.canGoBack: Boolean
    get() = currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavHostController.navigateBackWithUp() {
    if (canGoBack) navigateUp()
}