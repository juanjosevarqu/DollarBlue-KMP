package com.varqulabs.dollarblue.core.credits.ads

expect class RewardedAdService() {
    fun showRewardedAd(
        onReward: (amount: Int) -> Unit,
        onDismissed: () -> Unit = {},
        onError: (message: String) -> Unit = {},
        adUnitId: String = Constants.AD_REWARDED_ID
    )
}