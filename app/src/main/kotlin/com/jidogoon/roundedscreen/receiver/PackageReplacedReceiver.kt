package com.jidogoon.roundedscreen.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.jidogoon.roundedscreen.service.RoundedService

/**
 * Created by jidogoon on 2017. 5. 24..
 */
class PackageReplacedReceiver: BroadcastReceiver() {
    val TAG = javaClass.simpleName!!
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_MY_PACKAGE_REPLACED)) {
            Log.d(TAG, "onPackageReplaced")
            val serviceIntent = Intent(context, RoundedService::class.java)
            context?.startService(serviceIntent)
        }
    }
}