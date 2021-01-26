package com.mihanitylabs.adnitylib.rewardedad

import com.google.android.gms.ads.rewarded.RewardItem
import com.mihanitylabs.adnitylib.core.BaseConfig


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:33 PM         │
//└─────────────────────────────┘

class RewardedAdConfig(
    override val adId: String,
    val onSuccess: ((RewardItem) -> Unit)? = null,
    val onFailed: ((Exception) -> Unit)? = null,
    val onLoading: (() -> Unit)? = null,
    val onAdDismissed: (() -> Unit)? = null,
    val onAdShowedFullScreen: (() -> Unit)? = null
) : BaseConfig()
