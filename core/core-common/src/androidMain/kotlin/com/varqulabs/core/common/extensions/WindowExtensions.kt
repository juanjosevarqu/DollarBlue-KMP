package com.varqulabs.core.common.extensions

import android.os.Build
import android.view.Window
import android.view.WindowInsets

@Suppress("DEPRECATION")
fun Window.setStatusBarColorCompat(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
        decorView.setOnApplyWindowInsetsListener { view, insets ->
            val sbHeight = insets.getInsets(WindowInsets.Type.statusBars()).top
            view.setBackgroundColor(color)
            view.setPadding(0, sbHeight, 0, 0)
            insets
        }
    } else {
        statusBarColor = color
    }
}

@Suppress("DEPRECATION")
fun Window.setNavigationBarColorCompat(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
        decorView.setOnApplyWindowInsetsListener { view, insets ->
            val nbHeight = insets.getInsets(WindowInsets.Type.navigationBars()).bottom
            view.setBackgroundColor(color)
            view.setPadding(0, 0, 0, nbHeight)
            insets
        }
    } else {
        navigationBarColor = color
    }
}
