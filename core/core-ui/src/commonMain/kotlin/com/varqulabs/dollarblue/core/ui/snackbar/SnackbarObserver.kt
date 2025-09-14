package com.varqulabs.dollarblue.core.ui.snackbar

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SnackbarObserver(
    hostState: SnackbarHostState,
    navController: NavController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    var activeActionRoute by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        SnackBarController.events.collect { e ->
            when (e) {
                is SnackEvent.Info -> {
                    hostState.showSnackbar(
                        message = e.message,
                        duration = e.duration
                    )
                }
                is SnackEvent.Action -> {
                    val showRoute = currentRoute
                    activeActionRoute = showRoute

                    val result = hostState.showSnackbar(
                        message = e.message,
                        actionLabel = e.actionLabel,
                        withDismissAction = true,
                        duration = e.duration
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        CoroutineScope(e.originContext).launch { e.onAction() }
                    }

                    activeActionRoute = null
                }
            }
        }
    }

    LaunchedEffect(currentRoute, activeActionRoute) {
        val origin = activeActionRoute
        if (origin != null && currentRoute != origin) {
            hostState.currentSnackbarData?.dismiss()
        }
    }
}