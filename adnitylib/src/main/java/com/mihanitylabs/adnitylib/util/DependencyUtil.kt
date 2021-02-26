package com.mihanitylabs.adnitylib.util

import android.content.Context
import android.content.SharedPreferences

//  Code with ❤️
// ┌─────────────────────────────┐
// │ Created by Mirac Ozkan      │
// │ ─────────────────────────── │
// │ mirac.ozkan123@gmail.com    │            
// │ ─────────────────────────── │
// │ 2/26/2021 - 9:11 PM         │
// └─────────────────────────────┘

object DependencyUtil {
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(MIHANITY_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }
}
