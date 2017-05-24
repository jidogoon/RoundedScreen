package com.jidogoon.roundedscreen.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.jidogoon.roundedscreen.roundedview.RoundedView
import com.jidogoon.roundedscreen.roundedview.RoundedViewOptions

/**
 * Created by jidogoon on 2017. 5. 22..
 */
class RoundedService: Service() {

    val TAG = javaClass.simpleName

    companion object {
        val INTENT_KEY = "ROUNDED_KEY"
    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        showRoundedView(intent)
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
        roundedViewUI?.release()
        roundedViewUI = null
    }

    var roundedViewUI: RoundedView? = null

    fun showRoundedView(intent: Intent?) {
        val options = intent?.getParcelableExtra<RoundedViewOptions>(INTENT_KEY)
        if (roundedViewUI != null) {
            roundedViewUI?.invalidate(options!!)
            return
        }
        if (options == null)
            roundedViewUI = RoundedView(applicationContext)
        else
            roundedViewUI = RoundedView(applicationContext, options)
    }
}