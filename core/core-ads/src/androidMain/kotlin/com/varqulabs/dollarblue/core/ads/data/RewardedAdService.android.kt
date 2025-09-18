package com.varqulabs.dollarblue.core.ads.data

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

private lateinit var applicationContext: Context

private var currentActivity: Activity? = null

fun initMobileAds(context: Context) {
    applicationContext = context
    (applicationContext as Application).registerActivityLifecycleCallbacks(
        object : Application.ActivityLifecycleCallbacks {
            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
            }
            override fun onActivityPaused(activity: Activity) {
                if (currentActivity === activity) currentActivity = null
            }
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        }
    )
    MobileAds.initialize(applicationContext)
}

actual class RewardedAdService {

    actual fun showRewardedAd(
        onReward: (amount: Int) -> Unit,
        onDismissed: () -> Unit,
        onError: (message: String) -> Unit,
        adUnitId: String,
    ) {

        RewardedAd.load(
            /* context = */ applicationContext,
            /* adUnitId = */ adUnitId,
            /* adRequest = */ AdRequest.Builder().build(),
            /* loadCallback = */ object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    onError("Error al cargar anuncio: ${error.message}")
                    println(error.message)
                }
                override fun onAdLoaded(ad: RewardedAd) {
                    ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            onDismissed()
                        }
                        override fun onAdFailedToShowFullScreenContent(e: AdError) {
                            onError("Error al mostrar anuncio: ${e.message}")
                        }
                    }
                    currentActivity?.let {
                        ad.show(it) { rewardItem ->
                            onReward(rewardItem.amount)
                        }
                    }
                }
            }
        )
    }
}