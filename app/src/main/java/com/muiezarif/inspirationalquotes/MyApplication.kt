package com.muiezarif.inspirationalquotes

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds

class MyApplication:MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(applicationContext)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}