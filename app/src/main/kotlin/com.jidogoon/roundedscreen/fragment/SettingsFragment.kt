package com.jidogoon.roundedscreen.fragment

import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.jidogoon.roundedscreen.R

/**
 * Created by jidogoon on 2017. 5. 22..
 */
class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_settings)
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}