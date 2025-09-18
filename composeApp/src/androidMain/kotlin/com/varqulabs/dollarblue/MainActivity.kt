package com.varqulabs.dollarblue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowInsetsControllerCompat
import com.varqulabs.core.common.extensions.setNavigationBarColorCompat
import com.varqulabs.core.common.extensions.setStatusBarColorCompat
import com.varqulabs.dollarblue.core.conversions.data.local.initCurrencyConversionDatabase
import com.varqulabs.dollarblue.core.credits.ads.initMobileAds
import com.varqulabs.dollarblue.core.preferences.initPreferencesDataStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initMobileAds(applicationContext)
        initPreferencesDataStore(applicationContext)
        initCurrencyConversionDatabase(applicationContext)
        setContent {
            val window = LocalActivity.current?.window

            SideEffect {
                window?.run {
                    val windowsInsetsController = WindowInsetsControllerCompat(this, this.decorView)
                    windowsInsetsController.isAppearanceLightNavigationBars = false
                    this.setStatusBarColorCompat(getColor(R.color.whitescr))
                    this.setNavigationBarColorCompat(getColor(R.color.greenbtn))
                }
            }

            AppNavGraph()
        }
    }
}