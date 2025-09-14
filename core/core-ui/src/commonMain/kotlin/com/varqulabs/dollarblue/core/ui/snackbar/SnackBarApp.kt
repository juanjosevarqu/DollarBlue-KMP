package com.varqulabs.dollarblue.core.ui.snackbar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.varqulabs.core.designsystem.greenDark_65
import com.varqulabs.core.designsystem.white_96

@Composable
fun SnackBarApp(
    host: SnackbarHostState
) {
    SnackbarHost(host) { data ->
        Snackbar(
            snackbarData = data,
            shape = RoundedCornerShape(16.dp),
            containerColor = greenDark_65,
            contentColor = white_96,
        )
    }
}