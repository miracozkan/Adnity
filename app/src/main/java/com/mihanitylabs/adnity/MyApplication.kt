package com.mihanitylabs.adnity

import android.app.Application
import com.mihanitylabs.adnitylib.Adnity
import com.mihanitylabs.adnitylib.openappad.AppOpenAdConfig
import com.mihanitylabs.adnitylib.openappad.AppOpenAdManager

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
    private lateinit var appOpenAdManager: AppOpenAdManager

    private val appOpenAdConfig = AppOpenAdConfig(
        adId = APP_OPEN_AD_ID,
        onSuccess = {},
        onError = {}
    )

    override fun onCreate() {
        super.onCreate()
        appOpenAdManager = adnity.getAppOpenAdManager(this, appOpenAdConfig)
    }

    companion object {
        private const val APP_OPEN_AD_ID = "ca-app-pub-3940256099942544/3419835294"
    }
}
