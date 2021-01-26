package com.mihanitylabs.adnitylib.openappad

import com.google.android.gms.ads.appopen.AppOpenAd


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:32 PM         │
//└─────────────────────────────┘

class AppOpenAdConfig(
    val openAppAdId: String,
    val onSuccess: ((AppOpenAd) -> Unit)? = null,
    val onError: ((Exception) -> Unit)? = null
)
