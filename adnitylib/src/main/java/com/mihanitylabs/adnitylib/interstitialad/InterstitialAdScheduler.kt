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

class InterstitialAdScheduler private constructor() : LifecycleObserver {

    private var countDownTimer: CountDownTimer? = null
    private var timeInterval: Long? = null
    private var onTimeFinished: (() -> Unit?)? = null

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    fun init(timeIntervalInMinute: Int): InterstitialAdScheduler {
        timeInterval = timeIntervalInMinute * MINUTE_IN_MILLIS
        prepareTime()
        return this
    }

    fun setOnTimeFinishedListener(listener: () -> Unit) {
        onTimeFinished = listener
    }

    private fun prepareTime() {
        countDownTimer = object : CountDownTimer(timeInterval!!, SECOND_IN_MILLIS) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                onTimeFinished?.invoke()
                reloadTimer()
            }
        }
    }

    private fun startTimer() {
        countDownTimer?.start()
    }

    private fun reloadTimer() {
        prepareTime()
        startTimer()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
    }

    private fun clearTimer() {
        stopTimer()
        countDownTimer = null
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

    companion object {
        private const val SECOND_IN_MILLIS = 1000L
        private const val MINUTE_IN_MILLIS = 60 * SECOND_IN_MILLIS

        private var INSTANCE: InterstitialAdScheduler? = null

        fun getInstance(): InterstitialAdScheduler {
            return INSTANCE?.let {
                INSTANCE
            } ?: kotlin.run {
                INSTANCE = InterstitialAdScheduler()
                INSTANCE!!
            }
        }
    }
}
