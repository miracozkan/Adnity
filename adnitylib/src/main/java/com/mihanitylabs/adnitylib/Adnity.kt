package com.mihanitylabs.adnitylib

import android.app.Application
import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.mihanitylabs.adnitylib.bannerad.BannerAdManager
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAdManager
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAdScheduler
import com.mihanitylabs.adnitylib.openappad.AppOpenAdConfig
import com.mihanitylabs.adnitylib.openappad.AppOpenAdManager
import com.mihanitylabs.adnitylib.rewardedad.RewardedAdManager


//  Code with ❤️
// ┌─────────────────────────────┐
// │ Created by Mirac Ozkan      │
// │ ─────────────────────────── │
// │ mirac.ozkan123@gmail.com    │            
// │ ─────────────────────────── │
// │ 1/31/2021 - 8:52 PM         │
// └─────────────────────────────┘

class Adnity private constructor(context: Context) {

    init {
        MobileAds.initialize(context)
    }

    fun getRewardedAdManager() = RewardedAdManager.getInstance()

    fun getInterstitialAdManager() = InterstitialAdManager.getInstance()

    fun getInterstitialAdScheduler() = InterstitialAdScheduler.getInstance()

    fun getBannerAdManager() = BannerAdManager.getInstance()

    fun getAppOpenAdManager(application: Application, appOpenAdConfig: AppOpenAdConfig) =
        AppOpenAdManager.getInstance(application, appOpenAdConfig)

    companion object {
        private var INSTANCE: Adnity? = null

        fun getInstance(context: Context): Adnity {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Adnity(context)
                return INSTANCE!!
            }
        }
    }
}
