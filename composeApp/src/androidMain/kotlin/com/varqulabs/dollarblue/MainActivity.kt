package com.varqulabs.dollarblue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.varqulabs.dollarblue.core.conversions.data.local.initCurrencyConversionDatabase
import com.varqulabs.dollarblue.core.credits.ads.initMobileAds
import com.varqulabs.dollarblue.core.preferences.initPreferencesDataStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        initMobileAds(applicationContext)
        initPreferencesDataStore(applicationContext)
        initCurrencyConversionDatabase(applicationContext)
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}