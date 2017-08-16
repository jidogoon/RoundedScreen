package com.jidogoon.roundedscreen.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.jidogoon.roundedscreen.R
import com.jidogoon.roundedscreen.service.RoundedService
import com.jidogoon.roundedscreen.utils.PreferenceUtils

/**
 * Created by jidogoon on 2017. 5. 24..
 */
class RoundBroadcastReceiver : BroadcastReceiver() {
    val TAG = javaClass.simpleName!!
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_MY_PACKAGE_REPLACED -> {
                Log.d(TAG, "onPackageReplaced")
                val serviceIntent = Intent(context, RoundedService::class.java)
                context?.startService(serviceIntent)
            }
            Intent.ACTION_BOOT_COMPLETED -> {
                val startService = PreferenceUtils.getSharedPreferenceBoolean(context!!,
                        context.getString(R.string.pref_key_start_on_boot), false)
                Log.d(TAG, "onBootCompleted: " + startService)
                if (!startService)
                    return
                val serviceIntent = Intent(context, RoundedService::class.java)
                context.startService(serviceIntent)
            }
        }
    }
}