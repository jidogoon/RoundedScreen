package com.jidogoon.roundedscreen

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

/**
 * Created by jidogoon on 2017. 11. 17..
 */
class RoundedScreenApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCenter.start(this, "22012e62-18bd-4313-80f0-d3e6cc6ec48a", Analytics::class.java, Crashes::class.java)
    }
}