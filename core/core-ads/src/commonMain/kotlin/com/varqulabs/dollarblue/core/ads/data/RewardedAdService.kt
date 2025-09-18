package com.varqulabs.dollarblue.core.ads.data

import com.varqulabs.dollarblue.core.ads.domain.Constants

expect class RewardedAdService() {
    fun showRewardedAd(
        onReward: (amount: Int) -> Unit,
        onDismissed: () -> Unit = {},
        onError: (message: String) -> Unit = {},
        adUnitId: String = Constants.AD_REWARDED_ID
    )
}