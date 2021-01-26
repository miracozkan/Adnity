package com.mihanitylabs.adnitylib.interstitialad

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.mihanitylabs.adnitylib.util.Resource
import com.mihanitylabs.adnitylib.util.provideAdRequest


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:31 PM         │
//└─────────────────────────────┘

object InterstitialAd {

    fun displayInterstitial(activity: Activity, interstitialAdConfig: InterstitialAdConfig) {
        provideInterstitialAd(
            activity.applicationContext,
            interstitialAdConfig.adId
        ) { interstitialAdResource ->
            when (interstitialAdResource) {
                is Resource.Success -> {
                    interstitialAdResource.data.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                interstitialAdConfig.onDismissed?.invoke()
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                                interstitialAdConfig.onFailed?.invoke(Exception(adError?.message))
                            }

                            override fun onAdShowedFullScreenContent() {
                                interstitialAdConfig.onAdShowedFullScreen?.invoke(
                                    interstitialAdResource.data
                                )
                            }
                        }
                    interstitialAdResource.data.show(activity)
                }
                is Resource.Error -> interstitialAdConfig.onFailed?.invoke(interstitialAdResource.exception)
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
}