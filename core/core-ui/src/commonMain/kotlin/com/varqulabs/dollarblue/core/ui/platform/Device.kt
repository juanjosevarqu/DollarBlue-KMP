package com.varqulabs.dollarblue.core.ui.platform

import androidx.compose.runtime.Composable

enum class DeviceOrientation { PORTRAIT, LANDSCAPE }

@Composable
expect fun getDeviceOrientation(): DeviceOrientation

