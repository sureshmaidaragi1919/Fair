package com.mobile.fair.main

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class FairApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}