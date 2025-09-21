package com.example.equipohawknetwork

import android.app.Application
import com.example.equipohawknetwork.push.FcmNotificationHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FcmNotificationHelper.createDefaultChannel(this)
    }
}
