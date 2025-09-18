package com.varqulabs.dollarblue.core.ads.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

actual class RewardedAdService {
    // TODO: Replace with CocoaPods implementation
    actual fun showRewardedAd(
        onReward: (amount: Int) -> Unit,
        onDismissed: () -> Unit,
        onError: (message: String) -> Unit,
        adUnitId: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3.seconds)
            onReward(1)
            onDismissed()
        }
    }
}