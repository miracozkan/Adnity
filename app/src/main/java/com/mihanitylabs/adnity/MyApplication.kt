package com.mihanitylabs.adnity

import android.app.Application
import com.mihanitylabs.adnitylib.Adnity
import com.mihanitylabs.adnitylib.openappad.AppOpenAdConfig

// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:40 PM         │
//└─────────────────────────────┘

class MyApplication : Application() {

    private val adnity by lazy { Adnity.getInstance(this.applicationContext) }
    private val appOpenAdManager by lazy { adnity.getAppOpenAdManager(this, appOpenAdConfig) }

    private val appOpenAdConfig = AppOpenAdConfig(
        adId = APP_OPEN_AD_ID,
        onSuccess = {},
        onError = {}
    )

    companion object {
        private const val APP_OPEN_AD_ID = ""
    }
}
