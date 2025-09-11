package com.varqulabs.dollarblue.core.ui.platform

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getDeviceOrientation(): DeviceOrientation {
    val size = UIScreen.mainScreen.bounds.useContents { this.size }
    return if (size.height >= size.width)
        DeviceOrientation.PORTRAIT else DeviceOrientation.LANDSCAPE
}