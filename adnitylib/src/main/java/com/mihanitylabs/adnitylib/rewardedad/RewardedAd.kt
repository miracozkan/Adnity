package com.mihanitylabs.adnitylib.rewardedad

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.mihanitylabs.adnitylib.util.Resource
import com.mihanitylabs.adnitylib.util.provideAdRequest


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:33 PM         │
//└─────────────────────────────┘

object RewardedAd {

    fun displayRewardedAd(activity: Activity, rewardedAdConfig: RewardedAdConfig) {
        provideRewardedAd(activity, rewardedAdConfig.adId) { rewardedAdResource ->
            when (rewardedAdResource) {
                is Resource.Success -> {
                    rewardedAdResource.data.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                rewardedAdConfig.onAdDismissed?.invoke()
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                                rewardedAdConfig.onFailed?.invoke(Exception(adError?.message))
                            }

                            override fun onAdShowedFullScreenContent() {
                                rewardedAdConfig.onAdShowedFullScreen?.invoke()
                            }
                        }
                    rewardedAdResource.data.show(activity) { rewardedAdConfig.onSuccess?.invoke(it) }
                }
                is Resource.Error -> rewardedAdConfig.onFailed?.invoke(rewardedAdResource.exception)
                Resource.Loading -> rewardedAdConfig.onLoading?.invoke()
            }
        }
    }

    private fun provideRewardedAd(
        context: Context,
        rewardedAdId: String,
        onResult: (Resource<RewardedAd>) -> Unit
    ) {
        onResult.invoke(Resource.Loading)
        RewardedAd.load(
            context,
            rewardedAdId,
            provideAdRequest(),
            object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    onResult.invoke(Resource.Error(Exception(adError.message)))
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    onResult.invoke(Resource.Success(rewardedAd))
                }
            })
    }
}
