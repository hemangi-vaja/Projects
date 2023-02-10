package com.aspl.technometricspracticaltask

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : MultiDexApplication(){
    override fun onCreate() {
        MultiDex.install(this)
        super.onCreate()
    }
}