package com.mihanitylabs.adnity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.mihanitylabs.adnitylib.bannerad.BannerAd
import com.mihanitylabs.adnitylib.bannerad.BannerAdConfig
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAd
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAdConfig
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAdScheduler
import com.mihanitylabs.adnitylib.rewardedad.RewardedAd
import com.mihanitylabs.adnitylib.rewardedad.RewardedAdConfig

class MainActivity : AppCompatActivity() {

    private val interstitialAdConfig = InterstitialAdConfig(
        adId = INTERSTITIAL_AD_ID,
        onAdShowedFullScreen = {},
        onDismissed = {},
        onFailed = {},
        onLoading = {}
    )

    private val rewardedAdConfig = RewardedAdConfig(
        adId = REWARDED_AD_ID,
        onAdShowedFullScreen = {},
        onFailed = {},
        onLoading = {}
    )

    private val bannerAdConfig = BannerAdConfig(
        adId = BANNER_AD_ID,
        adSize = AdSize.BANNER,
        onAdClicked = {},
        onAdFailedToLoad = {},
        onAdLoaded = {},
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RewardedAd.displayRewardedAd(this, rewardedAdConfig)

        InterstitialAd.displayInterstitial(this, interstitialAdConfig)

        val adView: AdView = BannerAd.provideBannerAd(this, bannerAdConfig)

        val interstitialAdScheduler = InterstitialAdScheduler.getInstance().apply {
            init(5)
            setOnTimeFinishedListener {
                InterstitialAd.displayInterstitial(this@MainActivity, interstitialAdConfig)
            }
        }
    }

    companion object {
        private const val INTERSTITIAL_AD_ID = ""
        private const val BANNER_AD_ID = ""
        private const val REWARDED_AD_ID = ""
    }
}