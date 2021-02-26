package com.mihanitylabs.adnity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.mihanitylabs.adnitylib.Adnity
import com.mihanitylabs.adnitylib.bannerad.BannerAdConfig
import com.mihanitylabs.adnitylib.interstitialad.InterstitialAdConfig
import com.mihanitylabs.adnitylib.rewardedad.RewardedAdConfig

class MainActivity : AppCompatActivity() {

    //region Configs
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
    //endregion

    private val adnity by lazy { Adnity.getInstance(this) }
    private val rewardedAdManager by lazy { adnity.getRewardedAdManager() }
    private val interstitialAdManager by lazy { adnity.getInterstitialAdManager() }
    private val bannerAdManager by lazy { adnity.getBannerAdManager() }

    private val btnRewardedAd by lazy { findViewById<Button>(R.id.btnRewardedAd) }
    private val btnInterstitialAd by lazy { findViewById<Button>(R.id.btnInterstitialAd) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRewardedAd.setOnClickListener { onRewardedAdClick() }
        btnInterstitialAd.setOnClickListener { onInterstitialAdClick() }
    }

    private fun onRewardedAdClick() {
        rewardedAdManager.displayRewardedAd(this, rewardedAdConfig)
    }

    private fun onInterstitialAdClick() {
        interstitialAdManager.displayInterstitialDependOnInterval(this, interstitialAdConfig)
    }

    private fun provideBannerAd(): AdView {
        return bannerAdManager.provideBannerAd(this, bannerAdConfig)
    }

    companion object {
        private const val INTERSTITIAL_AD_ID = "ca-app-pub-3940256099942544/1033173712"
        private const val BANNER_AD_ID = "ca-app-pub-3940256099942544/6300978111"
        private const val REWARDED_AD_ID = "ca-app-pub-3940256099942544/5224354917"
    }
}
