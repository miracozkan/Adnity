package com.mihanitylabs.adnitylib.interstitialad

import android.app.Activity
import android.content.Context
import android.text.format.DateUtils.SECOND_IN_MILLIS
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.mihanitylabs.adnitylib.util.Resource
import com.mihanitylabs.adnitylib.util.provideAdRequest
import com.mihanitylabs.adnitylib.util.wasLoadTimeLessThanInterval


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:31 PM         │
//└─────────────────────────────┘

class InterstitialAdManager private constructor() {

    private var lastAdRequestAsTimeInMills: Long = System.currentTimeMillis()

    fun displayInterstitialDependOnInterval(
        activity: Activity,
        interstitialAdConfig: InterstitialAdConfig
    ) {
        if (!wasLoadTimeLessThanInterval(timeInterval, lastAdRequestAsTimeInMills)) return

        displayInterstitial(activity, interstitialAdConfig)
    }

    fun displayInterstitial(activity: Activity, interstitialAdConfig: InterstitialAdConfig) {
        provideInterstitialAd(activity.applicationContext, interstitialAdConfig.adId) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data.fullScreenContentCallback = object : FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            lastAdRequestAsTimeInMills = System.currentTimeMillis()
                            interstitialAdConfig.onDismissed?.invoke()
                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                            lastAdRequestAsTimeInMills = System.currentTimeMillis()
                            interstitialAdConfig.onFailed?.invoke(Exception(adError?.message))
                        }

                        override fun onAdShowedFullScreenContent() {
                            lastAdRequestAsTimeInMills = System.currentTimeMillis()
                            interstitialAdConfig.onAdShowedFullScreen?.invoke(resource.data)
                        }
                    }
                    resource.data.show(activity)
                }
                is Resource.Error -> {
                    lastAdRequestAsTimeInMills = System.currentTimeMillis()
                    interstitialAdConfig.onFailed?.invoke(resource.exception)
                }
                Resource.Loading -> interstitialAdConfig.onLoading?.invoke()
            }
        }
    }

    private fun provideInterstitialAd(
        context: Context,
        interstitialAdId: String,
        onResult: (Resource<InterstitialAd>) -> Unit
    ) {
        onResult.invoke(Resource.Loading)
        InterstitialAd.load(
            context,
            interstitialAdId,
            provideAdRequest(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    onResult.invoke(Resource.Error((Exception(adError.message))))
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    onResult.invoke(Resource.Success((interstitialAd)))
                }
            })
    }

    internal companion object {
        private var INSTANCE: InterstitialAdManager? = null
        private var timeInterval: Long = 30 * SECOND_IN_MILLIS

        fun getInstance(interval: Long = 30 * SECOND_IN_MILLIS): InterstitialAdManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = InterstitialAdManager()
                timeInterval = interval
                INSTANCE!!
            }
        }
    }
}
