package com.mihanitylabs.adnitylib.util

import android.app.Application
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:28 PM         │
//└─────────────────────────────┘

fun provideAdRequest(): AdRequest = AdRequest.Builder().build()

fun Application.initAMobileAds() = MobileAds.initialize(this)
