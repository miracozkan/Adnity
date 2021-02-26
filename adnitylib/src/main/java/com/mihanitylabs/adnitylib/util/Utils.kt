package com.mihanitylabs.adnitylib.util

import com.google.android.gms.ads.AdRequest
import java.util.*

// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:28 PM         │
//└─────────────────────────────┘

fun provideAdRequest(): AdRequest = AdRequest.Builder().build()

fun wasLoadTimeLessThanInterval(interval: Long, loadTime: Long): Boolean {
    val dateDifference = Date().time - loadTime
    return dateDifference < interval
}
