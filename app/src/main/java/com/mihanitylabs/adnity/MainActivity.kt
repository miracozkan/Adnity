package com.mihanitylabs.adnity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.mihanitylabs.adnitylib.Adnity
import com.mihanitylabs.adnitylib.bannerad.BannerAdConfig
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAdConfig
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

        val adnity = Adnity.getInstance(this)

        adnity.getRewardedAdManager().displayRewardedAd(this, rewardedAdConfig)

        adnity.getInterstitialAdManager().displayInterstitialDependOnInterval(this, interstitialAdConfig)

        val adView: AdView = adnity.getBannerAdManager().provideBannerAd(this, bannerAdConfig)

        adnity.getInterstitialAdScheduler().apply {
            init(5)
            setOnTimeFinishedListener {
                adnity.getInterstitialAdManager()
                    .displayInterstitialDependOnInterval(this@MainActivity, interstitialAdConfig)
            }
        }
    }

    companion object {
        private const val INTERSTITIAL_AD_ID = ""
        private const val BANNER_AD_ID = ""
        private const val REWARDED_AD_ID = ""
    }
}
