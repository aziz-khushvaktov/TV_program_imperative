package com.example.android_imperative

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImperativeApplication:Application() {
    private val TAG = ImperativeApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
    }
}