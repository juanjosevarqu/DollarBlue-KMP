package com.varqulabs.dollarblue.core.ui.platform

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun getDeviceOrientation(): DeviceOrientation {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        DeviceOrientation.PORTRAIT
    } else {
        DeviceOrientation.LANDSCAPE
    }
}