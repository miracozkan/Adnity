package com.mihanitylabs.adnitylib

import android.app.Application
import android.content.Context
import android.text.format.DateUtils.SECOND_IN_MILLIS
import com.google.android.gms.ads.MobileAds
import com.mihanitylabs.adnitylib.bannerad.BannerAdManager
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAdManager
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAdScheduler
import com.mihanitylabs.adnitylib.openappad.AppOpenAdConfig
import com.mihanitylabs.adnitylib.openappad.AppOpenAdManager
import com.mihanitylabs.adnitylib.rewardedad.RewardedAdManager
import com.mihanitylabs.adnitylib.util.DependencyUtil
import com.mihanitylabs.adnitylib.util.changeAppProState
import com.mihanitylabs.adnitylib.util.isAppPro


//  Code with ❤️
// ┌─────────────────────────────┐
// │ Created by Mirac Ozkan      │
// │ ─────────────────────────── │
// │ mirac.ozkan123@gmail.com    │            
// │ ─────────────────────────── │
// │ 1/31/2021 - 8:52 PM         │
// └─────────────────────────────┘

class Adnity private constructor(context: Context) {

    private val sharedPreferences by lazy { DependencyUtil.provideSharedPreferences(context) }

    init {
        MobileAds.initialize(context)
    }

    fun getRewardedAdManager() = RewardedAdManager.getInstance()

    fun getInterstitialAdManager(): InterstitialAdManager {
        return InterstitialAdManager.getInstance()
    }

    fun getInterstitialAdScheduler() = InterstitialAdScheduler.getInstance()

    fun getBannerAdManager() = BannerAdManager.getInstance()

    fun getAppOpenAdManager(application: Application, appOpenAdConfig: AppOpenAdConfig) =
        AppOpenAdManager.getInstance(application, appOpenAdConfig)

    fun isAppPro(): Boolean {
        return sharedPreferences.isAppPro()
    }

    fun changeAppProState(isPro: Boolean) {
        sharedPreferences.changeAppProState(isPro)
    }

    companion object {
        private var INSTANCE: Adnity? = null
        var interval = 30 * SECOND_IN_MILLIS

        fun getInstance(
            context: Context,
            interstitialTimeInterval: Long = 30 * SECOND_IN_MILLIS
        ): Adnity {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Adnity(context)
                interval = interstitialTimeInterval
                return INSTANCE!!
            }
        }

        fun changeInterstitialTimeInterval(interstitialTimeInterval: Long) {
            interval = interstitialTimeInterval
        }
    }
}
