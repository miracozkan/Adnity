package com.mihanitylabs.adnitylib.bannerad

import com.google.android.gms.ads.AdSize
import com.mihanitylabs.adnitylib.core.BaseConfig


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:29 PM         │
//└─────────────────────────────┘

class BannerAdConfig(
    override val adId: String,
    val adSize: AdSize = AdSize.BANNER,
    val onAdClicked: (() -> Unit)? = null,
    val onAdFailedToLoad: ((Exception) -> Unit)? = null,
    val onAdLoaded: (() -> Unit)? = null
) : BaseConfig()
