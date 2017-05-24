package com.jidogoon.roundedscreen.utils

import android.content.Context

/**
 * Created by dohyunji on 2017. 5. 22..
 */
class PreferenceUtils {
    companion object {
        fun getSharedPreferenceBoolean(context: Context, key: String, defaultValue: Boolean) : Boolean {
            val pref = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            return pref.getBoolean(key, defaultValue)
        }

        fun getSharedPreferenceInt(context: Context, key: String, defaultValue: Int) : Int {
            val pref = context.getSharedPreferences(key, Context.MODE_PRIVATE)
            return pref.getInt(key, defaultValue)
        }

        val PREF_NAME_ROUNDEDVIEW = "PREF_NAME_ROUNDEDVIEW"
        val PREF_KEY_ROUNDEDVIEW_TOPLEFT = "PREF_KEY_ROUNDEDVIEW_TOPLEFT"
        val PREF_KEY_ROUNDEDVIEW_TOPRIGHT = "PREF_KEY_ROUNDEDVIEW_TOPRIGHT"
        val PREF_KEY_ROUNDEDVIEW_BOTTOMLEFT = "PREF_KEY_ROUNDEDVIEW_BOTTOMLEFT"
        val PREF_KEY_ROUNDEDVIEW_BOTTOMRIGHT = "PREF_KEY_ROUNDEDVIEW_BOTTOMRIGHT"
        val PREF_KEY_ROUNDEDVIEW_SIZE = "PREF_KEY_ROUNDEDVIEW_SIZE"

        fun setAppPreferenceString(context: Context, prefName: String, key: String, value: String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getAppPreferenceString(context: Context, prefName: String, key: String, defaultValue: String) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(key, defaultValue)
        }

        fun setAppPreferenceBoolean(context: Context, prefName: String, key: String, value: Boolean) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun getAppPreferenceBoolean(context: Context, prefName: String, key: String, defaultValue: Boolean) : Boolean {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(key, defaultValue)
        }

        fun setAppPreferenceInt(context: Context, prefName: String, key: String, value: Int) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putInt(key, value)
            editor.apply()
        }

        fun getAppPreferenceInt(context: Context, prefName: String, key: String, defaultValue: Int) : Int {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getInt(key, defaultValue)
        }
    }
}