package com.mihanitylabs.adnitylib.util

import android.content.SharedPreferences

//  Code with ❤️
// ┌─────────────────────────────┐
// │ Created by Mirac Ozkan      │
// │ ─────────────────────────── │
// │ mirac.ozkan123@gmail.com    │            
// │ ─────────────────────────── │
// │ 2/26/2021 - 9:08 PM         │
// └─────────────────────────────┘

const val MIHANITY_SHARED_PREFERENCES_KEY = "mihanity_shared_preferences"
const val APP_PRO_KEY = "app_pro"

private fun getValue(sharedPreferences: SharedPreferences, key: String, defValue: Any): Any? {
    return with(sharedPreferences) {
        when (defValue) {
            is String -> getString(key, defValue)!!
            is Int -> getInt(key, defValue)
            is Long -> getLong(key, defValue)
            is Boolean -> getBoolean(key, defValue)
            else -> null
        }
    }
}

private fun putValue(sharedPreferences: SharedPreferences, key: String, value: Any) {
    with(sharedPreferences.edit()) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Boolean -> putBoolean(key, value)
            else -> putString(key, "")
        }.apply()
    }
}

fun SharedPreferences.isAppPro(): Boolean {
    return getValue(this, APP_PRO_KEY, false) as Boolean
}

fun SharedPreferences.changeAppProState(isPro: Boolean) {
    putValue(this, APP_PRO_KEY, isPro)
}
