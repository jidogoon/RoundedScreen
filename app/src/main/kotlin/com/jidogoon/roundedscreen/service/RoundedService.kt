package com.jidogoon.roundedscreen.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.jidogoon.roundedscreen.roundedview.RoundedView
import com.jidogoon.roundedscreen.roundedview.RoundedViewOptions

/**
 * Created by dohyunji on 2017. 5. 22..
 */
class RoundedService: Service() {
    companion object {
        val INTENT_KEY = "ROUNDED_KEY"
    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //return super.onStartCommand(intent, flags, startId)
        showRoundedView(intent)
        return START_STICKY
    }

    fun showRoundedView(intent: Intent?) {
        val options = intent?.getParcelableExtra<RoundedViewOptions>(INTENT_KEY)
        val roundedView = RoundedView(applicationContext, options!!)
    }
}