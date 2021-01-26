package com.mihanitylabs.adnitylib.interstitialad

import com.google.android.gms.ads.interstitial.InterstitialAd
import com.mihanitylabs.adnitylib.core.BaseConfig


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:32 PM         │
//└─────────────────────────────┘

class InterstitialAdConfig(
    override val adId: String,
    val onAdShowedFullScreen: ((InterstitialAd) -> Unit)? = null,
    val onFailed: ((Exception?) -> Unit)? = null,
    val onDismissed: (() -> Unit)? = null,
    val onLoading: (() -> Unit)? = null,
) : BaseConfig()
