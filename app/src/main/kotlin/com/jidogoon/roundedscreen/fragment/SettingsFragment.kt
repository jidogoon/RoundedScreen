package com.jidogoon.roundedscreen.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.jidogoon.roundedscreen.R
import com.jidogoon.roundedscreen.roundedview.RoundedViewOptions
import com.jidogoon.roundedscreen.service.RoundedService

/**
 * Created by jidogoon on 2017. 5. 22..
 */
class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_settings)
        val prefSize = findPreference(getString(R.string.pref_key_round_size))
        prefSize.onPreferenceChangeListener = this
        startRoundedService(RoundedViewOptions())
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        if (preference?.key.equals(getString(R.string.pref_key_round_size))) {
            val options = RoundedViewOptions()
            options.size = newValue as Int
            startRoundedService(options)
            return true
        }
        return false
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun startRoundedService(options: RoundedViewOptions) {
        val intent = Intent(context, RoundedService::class.java)
        intent.putExtra(RoundedService.INTENT_KEY, options)
        context.startService(intent)
    }
}