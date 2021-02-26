package com.mihanitylabs.adnitylib.openappad

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.mihanitylabs.adnitylib.util.provideAdRequest
import com.mihanitylabs.adnitylib.util.wasLoadTimeLessThanInterval
import java.util.*

// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:33 PM         │
//└─────────────────────────────┘

class AppOpenAdManager private constructor(
    private val application: Application,
    private val appOpenAdConfig: AppOpenAdConfig
) : Application.ActivityLifecycleCallbacks, LifecycleObserver {

    init {
        application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this@AppOpenAdManager)
    }

    private var loadTime = 0L
    private var currentActivity: Activity? = null
    private var appOpenAd: AppOpenAd? = null
    private lateinit var loadCallback: AppOpenAd.AppOpenAdLoadCallback
    private var isShowingAd = false

    private fun fetchAd() {
        if (isAdAvailable()) {
            return
        }
        loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAppOpenAdLoaded(ad: AppOpenAd) {
                appOpenAd = ad
                loadTime = Date().time
                appOpenAdConfig.onSuccess?.invoke(ad)
            }

            override fun onAppOpenAdFailedToLoad(loadAdError: LoadAdError) {
                appOpenAdConfig.onError?.invoke(Exception(loadAdError.cause?.message))
            }
        }
        AppOpenAd.load(
            application.applicationContext,
            appOpenAdConfig.adId,
            provideAdRequest(),
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
            loadCallback
        )
    }

    private fun showAdIfAvailable() {
        if (!isShowingAd && isAdAvailable()) {
            val fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    appOpenAd = null
                    isShowingAd = false
                    fetchAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    appOpenAdConfig.onError?.invoke(Exception(adError.cause?.message))
                }

                override fun onAdShowedFullScreenContent() {
                    isShowingAd = true
                }
            }
            appOpenAd?.show(currentActivity, fullScreenContentCallback)
        } else {
            fetchAd()
        }
    }

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null
                && wasLoadTimeLessThanInterval(4 * numMilliSecondsPerHour, loadTime)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        showAdIfAvailable()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }

    internal companion object {
        private const val TAG = "AppOpenAdManager"
        private const val numMilliSecondsPerHour = 3600000L

        private var INSTANCE: AppOpenAdManager? = null

        fun getInstance(
            application: Application,
            appOpenAdConfig: AppOpenAdConfig
        ): AppOpenAdManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = AppOpenAdManager(application, appOpenAdConfig)
                INSTANCE!!
            }
        }
    }
}
