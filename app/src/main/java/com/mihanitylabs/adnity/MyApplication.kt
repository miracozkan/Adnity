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

    private lateinit var appOpenAdManager: AppOpenAdManager

    private val appOpenAdConfig = AppOpenAdConfig(
        openAppAdId = APP_OPEN_AD_ID,
        onSuccess = {},
        onError = {}
    )

    override fun onCreate() {
        super.onCreate()
        val adnity = Adnity.getInstance(this.applicationContext)
        appOpenAdManager = adnity.getAppOpenAdManager(this, appOpenAdConfig)
    }

    companion object {
        private const val APP_OPEN_AD_ID = ""
    }
}
