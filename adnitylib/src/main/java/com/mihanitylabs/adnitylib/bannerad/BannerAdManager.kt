package com.mihanitylabs.adnitylib.bannerad

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.mihanitylabs.adnitylib.util.provideAdRequest


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:28 PM         │
//└─────────────────────────────┘

class BannerAdManager private constructor() {

    fun provideBannerAd(context: Context, bannerAdConfig: BannerAdConfig) = AdView(context).apply {
        adUnitId = bannerAdConfig.adId
        adSize = bannerAdConfig.adSize
        loadAd(provideAdRequest())
        adListener = object : AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError?) {
                super.onAdFailedToLoad(loadAdError)
                bannerAdConfig.onAdFailedToLoad?.invoke(Exception(loadAdError?.message))
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                bannerAdConfig.onAdLoaded?.invoke()
            }

            override fun onAdClicked() {
                super.onAdClicked()
                bannerAdConfig.onAdClicked?.invoke()
            }
        }
    }

    internal companion object {
        private var INSTANCE: BannerAdManager? = null

        fun getInstance(): BannerAdManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = BannerAdManager()
                INSTANCE!!
            }
        }
    }
}
