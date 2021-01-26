package com.mihanitylabs.adnitylib.interstitialad

import android.os.CountDownTimer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner


// Code with ❤️
//┌─────────────────────────────┐
//│ Created by Mirac Ozkan      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │            
//│ ─────────────────────────── │
//│ 1/26/2021 - 9:31 PM         │
//└─────────────────────────────┘

class InterstitialAdScheduler(
    private val timeInterval: Long = 30000L,
    private val onTimeFinished: () -> Unit
) : LifecycleObserver {

    private var countDownTimer: CountDownTimer? = null

    init {
        prepareTime(timeInterval)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    fun reloadTimer() {
        prepareTime(timeInterval)
        startTimer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        stopTimer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        startTimer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        clearTimer()
    }

    private fun prepareTime(timeInterval: Long) {
        countDownTimer = object : CountDownTimer(timeInterval, SECOND_IN_MILLIS) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                onTimeFinished.invoke()
            }
        }
    }

    private fun startTimer() {
        countDownTimer?.start()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
    }

    private fun clearTimer() {
        stopTimer()
        countDownTimer = null
    }

    companion object {
        private const val SECOND_IN_MILLIS = 1000L
    }
}
